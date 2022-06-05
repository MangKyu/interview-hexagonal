package com.mangkyu.employment.interview.app.quiz.converter;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.AddQuizRequest;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.GetQuizResponse;
import com.mangkyu.employment.interview.app.quiz.converter.QuizConverter;
import com.mangkyu.employment.interview.app.quiz.domain.Quiz;
import com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase.quiz;
import static com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase.quizEntity;
import static org.assertj.core.api.Assertions.assertThat;

class QuizConverterTest {

    @Test
    void 퀴즈로변환() {
        final QuizEntity quizEntity = quizEntity();
        final Quiz result = QuizConverter.INSTANCE.toQuiz(quizEntity);

        assertThat(result.getId()).isEqualTo(quizEntity.getId());
        assertThat(result.getTitle()).isEqualTo(quizEntity.getTitle());
        assertThat(result.getQuizCategory()).isEqualTo(quizEntity.getQuizCategory());
        assertThat(result.getQuizLevel()).isEqualTo(quizEntity.getQuizLevel());
    }

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
        final Quiz quiz = quiz();

        // when
        final GetQuizResponse result = QuizConverter.INSTANCE.toGetQuizResponse(quiz);

        // then
        assertThat(result.getTitle()).isEqualTo(quiz.getTitle());
        assertThat(result.getQuizLevelList().size()).isEqualTo(quiz.getQuizLevel().size());
        assertThat(result.getCategory()).isEqualTo(quiz.getQuizCategory().getTitle());
    }

}