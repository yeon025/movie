package com.example.spring.service;

import com.example.spring.entity.User;
import com.example.spring.repository.UserRepository;
import com.example.spring.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email){

        User user = userRepository.findByEmail(email).orElseThrow();

        return new CustomUserDetails(user);
    }
}
