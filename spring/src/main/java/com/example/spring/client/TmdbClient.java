package com.example.spring.client;

import com.example.spring.dto.movie.MovieDetailDto;
import com.example.spring.dto.movie.SyncMovieResponseDto;
import com.example.spring.dto.movie.TmdbMovieResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDate;


@Slf4j
@Component
@RequiredArgsConstructor
public class TmdbClient {


    @Value("${tmdb.api.key}")
    private String apiKey;

    private final WebClient tmdbWebClient;



    // 영화 목록 조회
    public TmdbMovieResponseDto getMovies(
            LocalDate startDate,
            LocalDate endDate,
            int page
    ) {

        return tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/discover/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("language", "ko-KR")
                        .queryParam("region", "KR")
                        .queryParam("sort_by", "primary_release_date.asc")
                        .queryParam("primary_release_date.gte", startDate)
                        .queryParam("primary_release_date.lte", endDate)
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(TmdbMovieResponseDto.class)
                .block();
    }



    // 상세 조회
    public MovieDetailDto getMovieDetail(Long movieId) {

        return tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}")
                        .queryParam("api_key", apiKey)
                        .queryParam("language", "ko-KR")
                        .build(movieId))
                .retrieve()
                .bodyToMono(MovieDetailDto.class)
                .block();
    }
}