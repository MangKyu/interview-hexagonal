package com.mangkyu.employment.interview.app.solvedquiz.domain.port.out;

import com.mangkyu.employment.interview.app.solvedquiz.adapter.persistence.SolvedQuizEntity;

public interface SaveSolvedQuizPort {
    void save(SolvedQuizEntity solvedQuizEntity);
}
