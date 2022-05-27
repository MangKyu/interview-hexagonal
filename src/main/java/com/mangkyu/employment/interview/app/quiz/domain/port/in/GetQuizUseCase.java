package com.mangkyu.employment.interview.app.quiz.domain.port.in;

import com.mangkyu.employment.interview.app.quiz.adapter.presentation.GetQuizResponse;

public interface GetQuizUseCase {

    GetQuizResponse getQuiz(final String resourceId);

}
