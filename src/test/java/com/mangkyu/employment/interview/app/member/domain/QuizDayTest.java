package com.mangkyu.employment.interview.app.member.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class QuizDayTest {

    @Test
    void QuizDay조회() {
        final LocalDateTime localDateTime = LocalDateTime.parse("2022-06-05T14:11:48.612236");

        final QuizDay quizDay = QuizDay.findQuizDay(localDateTime);

        assertThat(quizDay).isEqualTo(QuizDay.SUNDAY);
    }

}