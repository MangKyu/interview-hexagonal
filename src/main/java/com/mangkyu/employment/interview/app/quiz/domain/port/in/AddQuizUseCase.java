package com.mangkyu.employment.interview.app.quiz.domain.port.in;

import com.mangkyu.employment.interview.app.quiz.adapter.presentation.AddQuizRequest;

public interface AddQuizUseCase {

    void add(final AddQuizRequest addQuizRequest);

}
