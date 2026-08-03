package com.example.spring.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SyncMovieResponseDto {

    private Integer totalCount;

    public static SyncMovieResponseDto from(TmdbMovieResponseDto response) {
        return new SyncMovieResponseDto(response.getTotalResults());
    }
}
