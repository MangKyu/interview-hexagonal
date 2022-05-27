package com.mangkyu.employment.interview.app.quiz.adapter.persistence;

import com.mangkyu.employment.interview.app.quiz.domain.port.out.LoadQuizPort;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SaveQuizPort;
import com.mangkyu.employment.interview.app.quiz.errors.QuizErrorCode;
import com.mangkyu.employment.interview.app.quiz.errors.QuizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizPersistenceAdapter implements SaveQuizPort, LoadQuizPort {

    private final QuizPersistenceRepository quizPersistenceRepository;

    @Transactional
    public void add(final QuizEntity quizEntity) {
        quizPersistenceRepository.save(quizEntity);
    }

    @Override
    public QuizEntity findByResourceId(final String resourceId) {
        return quizPersistenceRepository.findByResourceId(resourceId)
                .orElseThrow(() -> new QuizException(QuizErrorCode.QUIZ_NOT_FOUND));
    }

}
