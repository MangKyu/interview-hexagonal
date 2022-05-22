package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;

public interface SaveQuizPort {

    void add(final QuizEntity quizEntity);

}
