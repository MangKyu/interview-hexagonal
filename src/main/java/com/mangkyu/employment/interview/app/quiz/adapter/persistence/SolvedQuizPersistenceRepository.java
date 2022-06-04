package com.mangkyu.employment.interview.app.quiz.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SolvedQuizPersistenceRepository extends JpaRepository<SolvedQuizEntity, Long> {
}
