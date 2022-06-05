package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.quiz.domain.UnsentQuizzes;

public interface SendQuizPort {
    int send(UnsentQuizzes unsentQuizzes);
}
