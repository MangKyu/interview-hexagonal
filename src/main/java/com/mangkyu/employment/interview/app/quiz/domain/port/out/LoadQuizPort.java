package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.member.domain.Member;
import com.mangkyu.employment.interview.app.quiz.domain.Quiz;
import com.mangkyu.employment.interview.app.quiz.domain.UnsentQuizzes;

public interface LoadQuizPort {

    Quiz findByResourceId(final String resourceId);

    UnsentQuizzes findUnsentQuizzes(final Member member);
}
