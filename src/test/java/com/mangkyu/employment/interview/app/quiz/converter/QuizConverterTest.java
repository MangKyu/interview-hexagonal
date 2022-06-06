package com.mangkyu.employment.interview.app.quiz.converter;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.AddQuizRequest;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.GetQuizResponse;
import com.mangkyu.employment.interview.app.quiz.domain.Quiz;
import com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

import static com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase.quiz;
import static org.assertj.core.api.Assertions.assertThat;

class QuizConverterTest {

    @Test
    void Quiz에서QuizEntity로변환() {
        final Quiz quiz = quiz();
        ReflectionTestUtils.setField(quiz, "id", 1L);

        final QuizEntity result = QuizConverter.INSTANCE.toQuizEntity(quiz);

        assertThat(result.getId()).isEqualTo(quiz.getId());
        assertThat(result.getTitle()).isEqualTo(quiz.getTitle());
        assertThat(result.getQuizCategory()).isEqualTo(quiz.getQuizCategory());
        assertThat(result.getQuizLevel()).isEqualTo(quiz.getQuizLevel());
    }

    @Test
    void QuizList에서QuizEntityList로변환() {
        final List<Quiz> quizList = Collections.singletonList(quiz());
        final List<QuizEntity> result = QuizConverter.INSTANCE.toQuizEntities(quizList);

        assertThat(result.get(0).getId()).isEqualTo(quizList.get(0).getId());
        assertThat(result.get(0).getTitle()).isEqualTo(quizList.get(0).getTitle());
        assertThat(result.get(0).getQuizCategory()).isEqualTo(quizList.get(0).getQuizCategory());
        assertThat(result.get(0).getQuizLevel()).isEqualTo(quizList.get(0).getQuizLevel());
    }

    @Test
    void AddQuizRequest에서QuizEntity로변환() {
        final AddQuizRequest addQuizRequest = QuizTestBase.addQuizRequest();
        final QuizEntity quizEntity = QuizConverter.INSTANCE.toQuizEntity(addQuizRequest);

        assertThat(quizEntity.getTitle()).isEqualTo(addQuizRequest.getTitle());
        assertThat(quizEntity.getQuizLevel()).isEqualTo(addQuizRequest.getQuizLevel());
        assertThat(quizEntity.getQuizCategory()).isEqualTo(addQuizRequest.getQuizCategory());
    }

    @Test
    void Quiz에서GetQuizResponse로변환() {
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