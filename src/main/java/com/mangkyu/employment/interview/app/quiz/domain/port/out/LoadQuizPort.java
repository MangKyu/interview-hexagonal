package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.member.domain.Member;
import com.mangkyu.employment.interview.app.quiz.domain.Quiz;

import java.util.List;

public interface LoadQuizPort {

    Quiz findByResourceId(final String resourceId);

    List<Quiz> findUnsentQuizList(final Member member);
}
