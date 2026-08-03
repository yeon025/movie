package com.example.spring.service;

import com.example.spring.client.FastApiClient;
import com.example.spring.client.TmdbClient;
import com.example.spring.dto.movie.MovieDetailDto;
import com.example.spring.dto.movie.MovieDto;
import com.example.spring.dto.movie.SyncMovieResponseDto;
import com.example.spring.dto.movie.TmdbMovieResponseDto;
import com.example.spring.entity.Genre;
import com.example.spring.entity.Movie;
import com.example.spring.entity.MovieGenre;
import com.example.spring.exception.CustomException;
import com.example.spring.exception.ErrorCode;
import com.example.spring.repository.GenreRepository;
import com.example.spring.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class AdminMovieService {

    private final TmdbClient tmdbClient;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final FastApiClient fastApiClient;

    @Transactional
    public SyncMovieResponseDto syncMovies() {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusMonths(1);

        TmdbMovieResponseDto first = tmdbClient.getMovies(start, end, 1);

        for (int page = 1; page <= first.getTotalPages(); page++) {

            TmdbMovieResponseDto response = tmdbClient.getMovies(start, end, page);

            for (MovieDto dto : response.getResults()) {

                // 시놉시스 없는 영화 제외
                if (dto.getSynopsis() == null || dto.getSynopsis().isBlank()) {
                    continue;
                }

                log.debug("시놉시스 : {}", dto.getSynopsis());

                MovieDetailDto detail = tmdbClient.getMovieDetail(dto.getId());

                // 장르 조회
                List<Genre> genres = detail.getGenres()
                        .stream()
                        .map(g -> genreRepository.findByTmdbId(g.getId())
                                .orElseThrow(() -> new CustomException(ErrorCode.MOVIE_GENRE_NOT_FOUND))
                        )
                        .toList();

                // 영화 생성
                Movie movie = Movie.builder()
                        .tmdbId(dto.getId())
                        .title(dto.getTitle())
                        .originalTitle(dto.getOriginalTitle())
                        .synopsis(dto.getSynopsis())
                        .runtime(detail.getRuntime())
                        .releaseDate(LocalDate.parse(dto.getReleaseDate()))
                        .posterUrl(dto.getPosterPath())
                        .backdropUrl(dto.getBackdropPath())
                        .rating(dto.getRating())
                        .build();

                // 영화-장르 관계 생성
                List<MovieGenre> movieGenres = genres.stream()
                        .map(genre -> MovieGenre.builder()
                                .movie(movie)
                                .genre(genre)
                                .build())
                        .toList();

                // 관계 연결
                movie.getGenres().addAll(movieGenres);

                // 저장
                movieRepository.save(movie);
            }
        }

        return SyncMovieResponseDto.from(first);
    }
}