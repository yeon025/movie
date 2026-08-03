package com.example.spring.service;

import com.example.spring.dto.auth.LoginRequestDto;
import com.example.spring.dto.auth.LoginResponseDto;
import com.example.spring.dto.auth.SignupRequestDto;
import com.example.spring.entity.Provider;
import com.example.spring.entity.Role;
import com.example.spring.entity.User;
import com.example.spring.exception.CustomException;
import com.example.spring.exception.ErrorCode;
import com.example.spring.repository.UserRepository;
import com.example.spring.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;


    public void signup(SignupRequestDto request) {

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .provider(Provider.LOCAL)
                .build();

        userRepository.save(user);
    }


    public LoginResponseDto login(LoginRequestDto request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_EMAIL_OR_PASSWORD));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_EMAIL_OR_PASSWORD);
        }

        String token = jwtProvider.createToken(user);

        return LoginResponseDto.builder()
                .accessToken(token)
                .build();
    }


    @Transactional
    public void logout() {

        // JWT 적용 전에는 서버에서 처리할 상태가 없음
        // 추후 RefreshToken 삭제 로직 추가
    }
}
