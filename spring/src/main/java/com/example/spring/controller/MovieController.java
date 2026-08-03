package com.example.spring.controller;

import com.example.spring.dto.ApiResponseDto;
import com.example.spring.dto.ai.RagRequestDto;
import com.example.spring.dto.ai.RagResponseDto;
import com.example.spring.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;


    @PostMapping("/rag/explanation")
    public ResponseEntity<ApiResponseDto<RagResponseDto>> ragExplanation(
            @RequestBody RagRequestDto request
    ) {
        RagResponseDto response = movieService.createRagExplanation(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDto.of("OK", "영화 추천했습니다.", response));
    }
}
