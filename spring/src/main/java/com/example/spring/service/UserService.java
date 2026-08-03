package com.example.spring.service;

import com.example.spring.dto.feedback.FeedbackResponseDto;
import com.example.spring.entity.FeedbackStatus;
import com.example.spring.repository.FeedbackRepository;
import com.example.spring.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final FeedbackRepository feedbackRepository;


    public List<FeedbackResponseDto> getFeedbacks(
            Long userId, FeedbackStatus status
    ) {

        return feedbackRepository
                .findByUserIdAndStatus(userId, status)
                .stream()
                .map(FeedbackResponseDto::from)
                .toList();
    }
}
