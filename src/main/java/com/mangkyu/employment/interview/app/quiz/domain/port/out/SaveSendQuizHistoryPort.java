package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.member.domain.Member;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.domain.Quiz;

import java.util.List;

public interface SaveSendQuizHistoryPort {
    void saveAll(Member member, List<Quiz> quizList);
}
