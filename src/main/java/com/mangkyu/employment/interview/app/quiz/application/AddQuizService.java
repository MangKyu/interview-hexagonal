package com.mangkyu.employment.interview.app.quiz.application;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.AddQuizRequest;
import com.mangkyu.employment.interview.app.quiz.converter.QuizConverter;
import com.mangkyu.employment.interview.app.quiz.domain.port.in.AddQuizUseCase;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SaveQuizPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddQuizService implements AddQuizUseCase {

    private final SaveQuizPort saveQuizPort;

    @Override
    public void add(final AddQuizRequest addQuizRequest) {
        final QuizEntity quizEntity = QuizConverter.INSTANCE.toQuizEntity(addQuizRequest);
        saveQuizPort.add(quizEntity);
    }

}
