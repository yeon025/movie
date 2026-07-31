package com.example.spring.controller;

import com.example.spring.dto.ApiResponseDto;
import com.example.spring.dto.movie.KmdbMovieResponseDto;
import com.example.spring.service.AdminMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminMovieController {

    private final AdminMovieService adminMovieService;

    @PostMapping("/movies/sync")
    public ApiResponseDto<KmdbMovieResponseDto> createMovie() {
        return null;
    }
}
