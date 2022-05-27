package com.mangkyu.employment.interview.app.quiz.domain.port.in;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.application.QuizService;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.LoadQuizPort;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SaveQuizPort;
import com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddQuizUseCaseTest {

    private AddQuizUseCase target;

    private SaveQuizPort saveQuizPort;

    @BeforeEach
    void init() {
        this.saveQuizPort = spy(SaveQuizPort.class);
        target = new QuizService(saveQuizPort, mock(LoadQuizPort.class));
    }

    @Test
    void 퀴즈추가() {
        target.add(QuizTestBase.addQuizRequest());

        verify(saveQuizPort, times(1)).add(any(QuizEntity.class));
    }

}