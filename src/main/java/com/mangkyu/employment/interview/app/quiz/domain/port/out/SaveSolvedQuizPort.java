package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;

public interface SaveSolvedQuizPort {
    void save(MemberEntity memberEntity, QuizEntity quizEntity);
}
