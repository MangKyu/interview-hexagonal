package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.quiz.domain.Quiz;
import com.mangkyu.employment.interview.app.quiz.domain.QuizCategory;
import com.mangkyu.employment.interview.app.quiz.domain.QuizLevel;

import java.util.List;
import java.util.Set;

public interface LoadQuizPort {

    Quiz findByResourceId(final String resourceId);

    List<Quiz> findUnsentQuizList(Long memberId, QuizLevel quizLevel, Set<QuizCategory> quizCategorySet);
}
