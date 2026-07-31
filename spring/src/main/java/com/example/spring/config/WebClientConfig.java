package com.example.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @Value("${kmdb.api.url}")
    private String kmdbUrl;

    @Bean
    public WebClient kmdbWebClient() {
        return WebClient.builder()
                .baseUrl(kmdbUrl)
                .build();
    }
}