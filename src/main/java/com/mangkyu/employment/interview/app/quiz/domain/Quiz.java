package com.mangkyu.employment.interview.app.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    private Long id;
    private String title;
    private QuizCategory quizCategory;
    private List<QuizLevel> quizLevel;

}
