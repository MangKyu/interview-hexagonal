package com.mangkyu.employment.interview.app.solvedquiz.testbase;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.solvedquiz.adapter.persistence.SolvedQuizEntity;

public class SolvedQuizTestBase {

    public static SolvedQuizEntity solvedQuizEntity(final MemberEntity member, final QuizEntity quiz) {
        return new SolvedQuizEntity(member, quiz);
    }

}
