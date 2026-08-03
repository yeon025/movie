package com.example.spring.service;

import com.example.spring.client.FastApiClient;
import com.example.spring.dto.ai.RagRequestDto;
import com.example.spring.dto.ai.RagResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final FastApiClient fastApiClient;


    public RagResponseDto createRagExplanation(RagRequestDto request) {

        return fastApiClient.generateExplanation(request);
    }
}
