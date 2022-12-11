package com.mangkyu.employment.interview.app.quiz.application;

import com.mangkyu.employment.interview.app.quiz.adapter.presentation.GetQuizResponse;
import com.mangkyu.employment.interview.app.quiz.converter.QuizConverter;
import com.mangkyu.employment.interview.app.quiz.domain.Quiz;
import com.mangkyu.employment.interview.app.quiz.domain.port.in.GetQuizUseCase;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.LoadQuizPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetQuizService implements GetQuizUseCase {

    private final LoadQuizPort loadQuizPort;

    @Override
    public GetQuizResponse getQuiz(final String resourceId) {
        final Quiz quiz = loadQuizPort.findByResourceId(resourceId);
        return QuizConverter.INSTANCE.toGetQuizResponse(quiz);
    }
}
