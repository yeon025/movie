package com.example.spring.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("user", "사용자"),
    ADMIN("admin", "관리자");

    private final String code;        // API 값
    private final String description; // UI 표시용
}
