package com.example.spring.controller;

import com.example.spring.dto.ApiResponseDto;
import com.example.spring.dto.auth.LoginRequestDto;
import com.example.spring.dto.auth.LoginResponseDto;
import com.example.spring.dto.auth.SignupRequestDto;
import com.example.spring.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<Void>> signup(
            @RequestBody SignupRequestDto request
    ){

        authService.signup(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDto.of("OK", "회원가입이 완료되었습니다."));
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> login(
            @RequestBody LoginRequestDto request
    ){
        LoginResponseDto response = authService.login(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDto.of("OK", "로그인이 완료되었습니다.", response));
    }


    @PostMapping("/logout")
    public ResponseEntity<ApiResponseDto<Void>> logout() {

        authService.logout();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDto.of("OK", "로그아웃이 완료되었습니다."));
    }
}
