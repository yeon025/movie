package com.example.spring.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeedbackStatus {
    LIKE("like", "좋아요"),
    DISLIKE("dislike", "싫어요"),
    NONE("none", "선택 안 함");

    private final String code;        // API 값
    private final String description; // UI 표시용
}