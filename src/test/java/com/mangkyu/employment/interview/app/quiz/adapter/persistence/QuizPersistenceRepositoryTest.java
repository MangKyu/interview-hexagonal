package com.mangkyu.employment.interview.app.quiz.adapter.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class QuizPersistenceRepositoryTest {

    @Autowired
    QuizPersistenceRepository repository;

}