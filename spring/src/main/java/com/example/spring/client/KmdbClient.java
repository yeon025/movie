package com.example.spring.client;

import com.example.spring.dto.movie.KmdbMovieResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class KmdbClient {

    @Value("${kmdb.api.key}")
    private String apiKey;

    private final WebClient kmdbWebClient;

    public KmdbMovieResponseDto getMovies(
            LocalDate from, LocalDate to, int startCount, int listCount
    ) {

        return kmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("collection", "kmdb_new2")
                        .queryParam("ServiceKey", apiKey)
                        .queryParam("detail", "Y")
                        .queryParam("releaseDts", from.format(DateTimeFormatter.BASIC_ISO_DATE))
                        .queryParam("releaseDte", to.format(DateTimeFormatter.BASIC_ISO_DATE))
                        .queryParam("startCount", startCount)
                        .queryParam("listCount", listCount)
                        .build())
                .retrieve()
                .bodyToMono(KmdbMovieResponseDto.class)
                .block();
    }
}