package com.mangkyu.employment.interview.app.quiz.testbase;

import com.mangkyu.employment.interview.app.quiz.adapter.presentation.GetQuizResponse;
import com.mangkyu.employment.interview.app.quiz.domain.QuizCategory;
import com.mangkyu.employment.interview.app.quiz.domain.QuizLevel;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.AddQuizRequest;
import com.mangkyu.employment.interview.app.quiz.converter.QuizConverter;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;

public class QuizTestBase {

    public final static String title = "title";
    public final static String quizResourceId = "b6d4d21e-af1a-47ab-b674-130a05166b50";
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
        final QuizEntity quizEntity = QuizConverter.INSTANCE.toQuizEntity(addQuizRequest());
        ReflectionTestUtils.setField(quizEntity, "createdAt", LocalDateTime.now());
        return quizEntity;
    }

    public static GetQuizResponse getQuizResponse() {
        final QuizEntity quizEntity = quizEntity();
        return QuizConverter.INSTANCE.toGetQuizResponse(quizEntity);
    }

}
