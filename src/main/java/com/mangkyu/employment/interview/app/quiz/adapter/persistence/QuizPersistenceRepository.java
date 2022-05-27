package com.mangkyu.employment.interview.app.quiz.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizPersistenceRepository extends JpaRepository<QuizEntity, Long> {
    Optional<QuizEntity> findByResourceId(String resourceId);

}
