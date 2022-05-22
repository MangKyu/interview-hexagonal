package com.mangkyu.employment.interview.app.quiz.application;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.AddQuizPort;
import com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddQuizUseCaseTest {

    @InjectMocks
    private QuizService target;

    @Spy
    private AddQuizPort addQuizPort;

    @Test
    void 퀴즈추가() {
        target.add(QuizTestBase.addQuizRequest());

        verify(addQuizPort, times(1)).add(any(QuizEntity.class));
    }

}