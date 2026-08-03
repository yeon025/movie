package com.example.spring.dto.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MovieDetailDto {

    private Long id;

    private Integer runtime;

    private List<GenreDto> genres;


    @Getter
    @NoArgsConstructor
    public static class GenreDto {

        private Long id;

        private String name;
    }
}
