package com.mangkyu.employment.interview.app.quiz.converter;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.AddQuizRequest;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.GetQuizResponse;
import com.mangkyu.employment.interview.app.quiz.converter.QuizConverter;
import com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

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

    @Test
    public void GetQuizResponse로변환() {
        // given
        final QuizEntity quiz = QuizTestBase.quizEntity();

        // when
        final GetQuizResponse result = QuizConverter.INSTANCE.toGetQuizResponse(quiz);

        // then
        assertThat(result.getResourceId()).isEqualTo(quiz.getResourceId());
        assertThat(result.getTitle()).isEqualTo(quiz.getTitle());
        assertThat(result.getQuizLevelList().size()).isEqualTo(quiz.getQuizLevel().size());
        assertThat(result.getCreatedAt()).isEqualTo(Timestamp.valueOf(quiz.getCreatedAt()).getTime());
        assertThat(result.getCategory()).isEqualTo(quiz.getQuizCategory().getTitle());
    }

}