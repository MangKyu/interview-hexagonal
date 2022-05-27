package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizPersistenceAdapter;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizPersistenceRepository;
import com.mangkyu.employment.interview.app.quiz.errors.QuizErrorCode;
import com.mangkyu.employment.interview.app.quiz.errors.QuizException;
import com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class LoadQuizPortTest {

    private LoadQuizPort target;

    @Autowired
    private QuizPersistenceRepository repository;

    @BeforeEach
    void init() {
        target = new QuizPersistenceAdapter(repository);
    }

    @Test
    void 리소스아이디로퀴즈조회() {
        final QuizEntity quiz = QuizTestBase.quizEntity();
        repository.save(quiz);

        final QuizEntity result = target.findByResourceId(quiz.getResourceId());

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(quiz.getTitle());
    }

    @Test
    void 리소스아이디로퀴즈조회_퀴즈없음() {
        final QuizEntity quiz = QuizTestBase.quizEntity();

        final QuizException result = assertThrows(QuizException.class, () -> target.findByResourceId(quiz.getResourceId()));

        // then
        assertThat(result.getErrorCode()).isEqualTo(QuizErrorCode.QUIZ_NOT_FOUND);
    }

}