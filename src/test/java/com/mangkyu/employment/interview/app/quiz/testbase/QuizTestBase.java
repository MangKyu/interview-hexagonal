package com.mangkyu.employment.interview.app.quiz.testbase;

import com.mangkyu.employment.interview.app.enums.QuizCategory;
import com.mangkyu.employment.interview.app.enums.QuizLevel;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.AddQuizRequest;
import com.mangkyu.employment.interview.app.quiz.application.converter.QuizConverter;

import java.util.List;

public class QuizTestBase {

    private final static String title = "title";
    private final static QuizCategory quizCategory = QuizCategory.JAVA;
    private final static List<QuizLevel> quizLevelList = List.of(QuizLevel.NEW, QuizLevel.JUNIOR);

    public static AddQuizRequest addQuizRequest() {
        return AddQuizRequest.builder()
                .title(title)
                .quizCategory(quizCategory)
                .quizLevel(quizLevelList)
                .build();
    }

    public static QuizEntity quizEntity() {
        return QuizConverter.INSTANCE.toQuizEntity(addQuizRequest());
    }


}
