package com.mangkyu.employment.interview.app.quiz.domain.port.in;

import com.mangkyu.employment.interview.app.quiz.adapter.presentation.GetQuizResponse;
import com.mangkyu.employment.interview.app.quiz.application.QuizService;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.LoadQuizPort;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SaveQuizPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase.quiz;
import static com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase.quizResourceId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class GetQuizUseCaseTest {

    private GetQuizUseCase target;
    private LoadQuizPort loadQuizPort;

    @BeforeEach
    void init() {
        this.loadQuizPort = mock(LoadQuizPort.class);
        target = new QuizService(mock(SaveQuizPort.class), loadQuizPort);
    }

    @Test
    void 퀴즈조회() {
        doReturn(quiz())
                .when(loadQuizPort)
                .findByResourceId(quizResourceId);

        final GetQuizResponse result = target.getQuiz(quizResourceId);
        assertThat(result).isNotNull();
    }

}