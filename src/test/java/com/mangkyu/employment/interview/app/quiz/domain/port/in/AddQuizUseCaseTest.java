package com.mangkyu.employment.interview.app.quiz.domain.port.in;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.application.AddQuizService;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SaveQuizPort;
import com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddQuizUseCaseTest {

    private AddQuizUseCase target;

    private SaveQuizPort saveQuizPort;

    @BeforeEach
    void init() {
        this.saveQuizPort = mock(SaveQuizPort.class);
        target = new AddQuizService(saveQuizPort);
    }

    @Test
    void 퀴즈추가() {
        target.add(QuizTestBase.addQuizRequest());

        verify(saveQuizPort, times(1)).add(any(QuizEntity.class));
    }

}