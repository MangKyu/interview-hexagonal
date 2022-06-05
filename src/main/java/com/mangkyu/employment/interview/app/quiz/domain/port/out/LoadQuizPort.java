package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.quiz.domain.Quiz;

public interface LoadQuizPort {

    Quiz findByResourceId(final String resourceId);

}
