package com.example.spring.dto.feedback;

import com.example.spring.entity.Feedback;
import com.example.spring.entity.FeedbackStatus;
import com.example.spring.entity.Movie;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class FeedbackResponseDto {

    private Long feedbackId;

    private Long movieId;

    private String title;

    private String posterUrl;

    private LocalDate releaseDate;

    private FeedbackStatus status;


    public static FeedbackResponseDto from(Feedback feedback) {

        Movie movie = feedback.getMovie();

        return FeedbackResponseDto.builder()
                .feedbackId(feedback.getId())
                .movieId(movie.getId())
                .title(movie.getTitle())
                .posterUrl(movie.getPosterUrl())
                .releaseDate(movie.getReleaseDate())
                .status(feedback.getStatus())
                .build();
    }
}
