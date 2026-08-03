package com.example.spring.client;

import com.example.spring.dto.ai.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.Map;


@Slf4j
@Component
@RequiredArgsConstructor
public class FastApiClient {

    private final WebClient fastApiWebClient;


    public RagResponseDto generateExplanation(RagRequestDto request) {

        return fastApiWebClient.post()
                .uri("/api/movies/rag/explanation")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(RagResponseDto.class)
                .block();
    }
}
