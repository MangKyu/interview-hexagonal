package com.mangkyu.employment.interview.app.quiz.application.converter;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.AddQuizRequest;
import com.mangkyu.employment.interview.app.quiz.converter.QuizConverter;
import com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QuizConverterTest {

    @Test
    void 퀴즈엔티티로변환() {
        final AddQuizRequest addQuizRequest = QuizTestBase.addQuizRequest();
        final QuizEntity quizEntity = QuizConverter.INSTANCE.toQuizEntity(addQuizRequest);

        assertThat(quizEntity.getTitle()).isEqualTo(addQuizRequest.getTitle());
        assertThat(quizEntity.getQuizLevel()).isEqualTo(addQuizRequest.getQuizLevel());
        assertThat(quizEntity.getQuizCategory()).isEqualTo(addQuizRequest.getQuizCategory());
    }

}