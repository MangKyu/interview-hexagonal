package com.mangkyu.employment.interview.app.quiz.adapter.presentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class GetQuizResponse {

    private final String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String category;

    private final List<String> quizLevelList;

}