package com.example.spring.service;

import com.example.spring.dto.feedback.FeedbackResponseDto;
import com.example.spring.entity.Feedback;
import com.example.spring.entity.FeedbackStatus;
import com.example.spring.entity.Movie;
import com.example.spring.entity.User;
import com.example.spring.exception.CustomException;
import com.example.spring.exception.ErrorCode;
import com.example.spring.repository.FeedbackRepository;
import com.example.spring.repository.MovieRepository;
import com.example.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;


    public List<FeedbackResponseDto> getFeedbacks(Long userId, FeedbackStatus status) {

        return feedbackRepository
                .findByUserIdAndStatus(userId, status)
                .stream()
                .map(FeedbackResponseDto::from)
                .toList();
    }


    public void updatePreference(Long userId, Long movieId, FeedbackStatus status) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new CustomException(ErrorCode.MOVIE_NOT_FOUND));

        Feedback feedback = feedbackRepository
                .findByUserIdAndMovieId(userId, movieId)
                .orElse(null);

        if (feedback != null) {
            feedback.updateStatus(status);
        } else {
            Feedback newFeedback = Feedback.builder()
                    .user(user)
                    .movie(movie)
                    .status(status)
                    .build();

            feedbackRepository.save(newFeedback);
        }
    }
}
