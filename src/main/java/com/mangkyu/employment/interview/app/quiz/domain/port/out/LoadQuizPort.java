package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;

public interface LoadQuizPort {

    QuizEntity findByResourceId(final String resourceId);

}
