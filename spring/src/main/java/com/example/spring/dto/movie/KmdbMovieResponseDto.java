package com.example.spring.dto.movie;

import lombok.Getter;
import java.util.List;


@Getter
public class KmdbMovieResponseDto {

    private List<MovieDto> movieList;
}
