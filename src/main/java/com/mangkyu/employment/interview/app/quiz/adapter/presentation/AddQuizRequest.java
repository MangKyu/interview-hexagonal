package com.mangkyu.employment.interview.app.quiz.adapter.presentation;

import com.mangkyu.employment.interview.app.quiz.domain.QuizCategory;
import com.mangkyu.employment.interview.app.quiz.domain.QuizLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class AddQuizRequest {

    private final String resourceId = String.valueOf(UUID.randomUUID());

    @NotBlank
    private final String title;

    @NotNull
    private final QuizCategory quizCategory;

    @NotEmpty
    private final List<QuizLevel> quizLevel;

}
