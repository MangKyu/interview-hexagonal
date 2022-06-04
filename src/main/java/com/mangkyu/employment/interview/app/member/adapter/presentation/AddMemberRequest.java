package com.mangkyu.employment.interview.app.member.adapter.presentation;

import com.mangkyu.employment.interview.app.member.domain.QuizDay;
import com.mangkyu.employment.interview.app.quiz.domain.QuizCategory;
import com.mangkyu.employment.interview.app.quiz.domain.QuizLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class AddMemberRequest {

    private final String resourceId = String.valueOf(UUID.randomUUID());

    @Email
    @NotBlank
    private final String email;

    @NotNull
    private final QuizLevel quizLevel;

    @Range(min = 1, max = 5)
    private final Integer quizSize = 3;

    @NotEmpty
    private final Set<QuizDay> quizDaySet;

    @NotEmpty
    private final Set<QuizCategory> quizCategorySet;

}