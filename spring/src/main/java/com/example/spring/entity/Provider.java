package com.example.spring.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Provider {
    LOCAL("local", "로컬"),
    KAKAO("kakao", "카카오");

    private final String code;        // API 값
    private final String description; // UI 표시용
}
