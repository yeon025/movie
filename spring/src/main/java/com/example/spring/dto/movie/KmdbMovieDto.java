package com.example.spring.dto.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KmdbMovieDto {

    private String title;

    private String titleEng;

    private String prodYear;

    private String genre;

    private String nation;

    private String runtime;

    @JsonProperty("repRlsDate")
    private String releaseDate;

    private String posters;

    private Plots plots;

    private Directors directors;

    private Actors actors;

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Plots {
        private List<Plot> plot;
    }

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Plot {
        private String plotText;
    }

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Directors {
        private List<Director> director;
    }

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Director {
        private String directorNm;
    }

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Actors {
        private List<Actor> actor;
    }

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Actor {
        private String actorNm;
    }
}
