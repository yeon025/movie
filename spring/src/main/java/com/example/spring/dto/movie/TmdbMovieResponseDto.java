package com.example.spring.dto.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.List;


@Getter
public class TmdbMovieResponseDto {

    private int page;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_results")
    private int totalResults;

    private List<MovieDto> results;
}
