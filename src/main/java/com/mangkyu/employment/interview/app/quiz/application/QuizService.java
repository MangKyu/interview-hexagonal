package com.mangkyu.employment.interview.app.quiz.application;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.AddQuizRequest;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.GetQuizResponse;
import com.mangkyu.employment.interview.app.quiz.converter.QuizConverter;
import com.mangkyu.employment.interview.app.quiz.domain.port.in.AddQuizUseCase;
import com.mangkyu.employment.interview.app.quiz.domain.port.in.GetQuizUseCase;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.LoadQuizPort;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SaveQuizPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService implements AddQuizUseCase, GetQuizUseCase {

    private final SaveQuizPort addQuizPort;
    private final LoadQuizPort loadQuizPort;

    @Override
    public void add(final AddQuizRequest addQuizRequest) {
        final QuizEntity quizEntity = QuizConverter.INSTANCE.toQuizEntity(addQuizRequest);
        addQuizPort.add(quizEntity);
    }

    @Override
    public GetQuizResponse getQuiz(final String resourceId) {
        final QuizEntity quizEntity = loadQuizPort.findByResourceId(resourceId);
        return QuizConverter.INSTANCE.toGetQuizResponse(quizEntity);
    }
}
