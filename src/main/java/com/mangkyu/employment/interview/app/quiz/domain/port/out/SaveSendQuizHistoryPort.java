package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;

import java.util.List;

public interface SaveSendQuizHistoryPort {
    void save(MemberEntity memberEntity, QuizEntity quizEntity);

    void saveAll(MemberEntity memberEntity, List<QuizEntity> quizEntityList);
}
