package com.example.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @Value("${tmdb.api.url}")
    private String tmdbUrl;

    @Value("${FASTAPI_BASE_URL}")
    private String fastApiBaseUrl;

    @Bean
    public WebClient tmdbWebClient() {
        return WebClient.builder()
                .baseUrl(tmdbUrl)
                .build();
    }

    @Bean
    public WebClient fastApiWebClient(){

        return WebClient.builder()
                .baseUrl(fastApiBaseUrl)
                .build();
    }
}