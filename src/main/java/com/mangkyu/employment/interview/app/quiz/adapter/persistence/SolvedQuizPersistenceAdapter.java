package com.mangkyu.employment.interview.app.quiz.adapter.persistence;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SaveSolvedQuizPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SolvedQuizPersistenceAdapter implements SaveSolvedQuizPort {

    private final SolvedQuizPersistenceRepository repository;

    @Override
    public void save(final MemberEntity memberEntity, final QuizEntity quizEntity) {
        repository.save(new SolvedQuizEntity(memberEntity, quizEntity));
    }

}
