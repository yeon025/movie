package com.example.spring.dto.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private String title;
    private String titleEng;
    private String runtime;
    private String genre;
    private String releaseDate;
    private String nation;
    private String posterUrl;
    private String director;
    private String actors;
    private String synopsis;

    public static MovieDto from(KmdbMovieDto movie) {

        return MovieDto.builder()
                .title(movie.getTitle())
                .titleEng(movie.getTitleEng())
                .genre(movie.getGenre())
                .synopsis(getSynopsis(movie))
                .posterUrl(movie.getPosters())
                .director(getDirectors(movie))
                .actors(getActors(movie))
                .nation(movie.getNation())
                .runtime(movie.getRuntime())
                .releaseDate(movie.getReleaseDate())
                .build();
    }

    private static String getSynopsis(KmdbMovieDto movie) {
        if (movie.getPlots() == null || movie.getPlots().getPlot().isEmpty()) {
            return null;
        }
        return movie.getPlots().getPlot().getFirst().getPlotText();
    }

    private static String getDirectors(KmdbMovieDto movie) {
        if (movie.getDirectors() == null || movie.getDirectors().getDirector() == null) {
            return null;
        }

        return movie.getDirectors().getDirector().stream()
                .map(KmdbMovieDto.Director::getDirectorNm)
                .collect(Collectors.joining(", "));
    }

    private static String getActors(KmdbMovieDto movie) {
        if (movie.getActors() == null || movie.getActors().getActor() == null) {
            return null;
        }

        return movie.getActors().getActor().stream()
                .map(KmdbMovieDto.Actor::getActorNm)
                .collect(Collectors.joining(", "));
    }
}