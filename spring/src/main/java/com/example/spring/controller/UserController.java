package com.example.spring.controller;

import com.example.spring.dto.ApiResponseDto;
import com.example.spring.dto.feedback.FeedbackRequestDto;
import com.example.spring.dto.feedback.FeedbackResponseDto;
import com.example.spring.entity.FeedbackStatus;
import com.example.spring.security.CustomUserDetails;
import com.example.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/me")
public class UserController {

    private final UserService userService;


    @GetMapping("/likes")
    public ResponseEntity<ApiResponseDto<List<FeedbackResponseDto>>> getLikes(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        List<FeedbackResponseDto> response = userService.getFeedbacks(userDetails.getUser().getId(), FeedbackStatus.LIKE);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDto.of("OK", "좋아요 목록을 조회했습니다.", response));
    }


    @GetMapping("/dislikes")
    public ResponseEntity<ApiResponseDto<List<FeedbackResponseDto>>> getDislikes(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        List<FeedbackResponseDto> response = userService.getFeedbacks(userDetails.getUser().getId(), FeedbackStatus.DISLIKE);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDto.of("OK", "싫어요 목록을 조회했습니다.", response));
    }


    @PutMapping("/movies/{movieId}/preference")
    public ResponseEntity<ApiResponseDto<Void>> updatePreference(
            @PathVariable Long movieId,
            @RequestBody FeedbackRequestDto request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        userService.updatePreference(userDetails.getUser().getId(), movieId, request.getStatus());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDto.of("OK", "영화 선호도를 변경했습니다."));
    }
}
