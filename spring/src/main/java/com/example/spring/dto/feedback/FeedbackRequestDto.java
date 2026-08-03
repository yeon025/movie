package com.example.spring.dto.feedback;

import com.example.spring.entity.FeedbackStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedbackRequestDto {

    @NotNull
    private FeedbackStatus status;
}
