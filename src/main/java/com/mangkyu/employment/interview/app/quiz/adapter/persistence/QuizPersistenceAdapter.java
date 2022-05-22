package com.mangkyu.employment.interview.app.quiz.adapter.persistence;

import com.mangkyu.employment.interview.app.quiz.domain.port.out.SaveQuizPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizPersistenceAdapter implements SaveQuizPort {

    private final QuizPersistenceRepository quizPersistenceRepository;

    @Transactional
    public void add(final QuizEntity quizEntity) {
        quizPersistenceRepository.save(quizEntity);
    }

}
