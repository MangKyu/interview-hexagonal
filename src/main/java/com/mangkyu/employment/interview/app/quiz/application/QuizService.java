package com.mangkyu.employment.interview.app.quiz.application;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.AddQuizRequest;
import com.mangkyu.employment.interview.app.quiz.application.converter.QuizConverter;
import com.mangkyu.employment.interview.app.quiz.domain.port.in.AddQuizUseCase;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.AddQuizPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService implements AddQuizUseCase {

    private final AddQuizPort addQuizPort;
    private final QuizConverter quizConverter;

    @Override
    public void add(final AddQuizRequest addQuizRequest) {
        final QuizEntity quizEntity = quizConverter.INSTANCE.toQuizEntity(addQuizRequest);
        addQuizPort.add(quizEntity);
    }

}
