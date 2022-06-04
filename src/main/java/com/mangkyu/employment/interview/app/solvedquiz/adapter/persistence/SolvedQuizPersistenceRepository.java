package com.mangkyu.employment.interview.app.solvedquiz.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SolvedQuizPersistenceRepository extends JpaRepository<SolvedQuizEntity, Long> {
}
