package com.mangkyu.employment.interview.app.solvedquiz.adapter.persistence;

import com.mangkyu.employment.interview.app.solvedquiz.domain.port.out.SaveSolvedQuizPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SolvedQuizPersistenceAdapter implements SaveSolvedQuizPort {

    private final SolvedQuizPersistenceRepository repository;

    @Override
    public void save(final SolvedQuizEntity solvedQuizEntity) {
        repository.save(solvedQuizEntity);
    }

}
