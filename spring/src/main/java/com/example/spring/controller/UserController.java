package com.example.spring.controller;

import com.example.spring.dto.ApiResponseDto;
import com.example.spring.dto.auth.LoginResponseDto;
import com.example.spring.dto.feedback.FeedbackResponseDto;
import com.example.spring.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/me")
public class UserController {

    @GetMapping("/me/likes")
    public ResponseEntity<List<FeedbackResponseDto>> getLikes(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        return ResponseEntity.ok(
                userService.getFeedbacks(
                        userDetails.getUser().getId(),
                        FeedbackStatus.LIKE
                )
        );
    }
}
