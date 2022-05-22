package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizPersistenceAdapter;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizPersistenceRepository;
import com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SaveQuizPortTest {

    private SaveQuizPort target;

    @Autowired
    private QuizPersistenceRepository quizPersistenceRepository;

    @BeforeEach
    void init() {
        target = new QuizPersistenceAdapter(quizPersistenceRepository);
    }

    @Test
    void 퀴즈엔티티추가성공() {
        target.add(QuizTestBase.quizEntity());

        assertThat(quizPersistenceRepository.count()).isOne();
    }

}