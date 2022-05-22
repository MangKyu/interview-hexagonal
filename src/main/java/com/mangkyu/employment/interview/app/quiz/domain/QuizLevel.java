package com.mangkyu.employment.interview.app.quiz.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuizLevel {

    NEW("New", "신입"),
    JUNIOR("Junior", "주니어"),
    SENIOR("Senior", "시니어"),
    ;

    private final String title;
    private final String desc;

}