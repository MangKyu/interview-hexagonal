package com.mangkyu.employment.interview.app.solvedquiz.adapter.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SolvedQuizPersistenceRepositoryTest {

    @Autowired
    private SolvedQuizPersistenceRepository target;

    @Test
    void temp() {
        assertThat(target).isNotNull();
    }

}