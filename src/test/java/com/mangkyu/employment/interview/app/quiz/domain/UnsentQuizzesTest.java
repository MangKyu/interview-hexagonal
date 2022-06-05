package com.mangkyu.employment.interview.app.quiz.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase.quiz;
import static org.assertj.core.api.Assertions.assertThat;

class UnsentQuizzesTest {

    private UnsentQuizzes target;
    private final int sendSize = 3;

    @BeforeEach
    void init() {
        target = new UnsentQuizzes(List.of(quiz(), quiz(), quiz(), quiz()), sendSize);
    }

    @Test
    void 마지막문제여부조회() {
        assertThat(target.isLast()).isFalse();
    }

    @Test
    void 사이즈조회() {
        assertThat(target.size()).isEqualTo(4);
    }

    @Test
    void 랜덤문제조회_마지막문제임() {
        target = new UnsentQuizzes(List.of(quiz(), quiz(), quiz()), sendSize);

        final List<Quiz> result = target.getRandomQuizUnderSize();

        assertThat(result.size()).isEqualTo(sendSize);
    }

    @Test
    void 랜덤문제조회_마지막문제가아님() {
        final List<Quiz> result = target.getRandomQuizUnderSize();
        assertThat(result.size()).isEqualTo(sendSize);
    }

}