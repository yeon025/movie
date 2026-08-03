package com.example.spring.controller;

import com.example.spring.dto.ApiResponseDto;
import com.example.spring.dto.movie.SyncMovieResponseDto;
import com.example.spring.service.AdminMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminMovieController {

    private final AdminMovieService adminMovieService;


    @GetMapping("/movies/sync")
    public  ResponseEntity<ApiResponseDto<SyncMovieResponseDto>> syncMovie() {

        SyncMovieResponseDto response = adminMovieService.syncMovies();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDto.of("OK", "영화 동기화를 완료했습니다.", response));
    }
}
