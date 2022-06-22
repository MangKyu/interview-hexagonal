package com.mangkyu.employment.interview.app.quiz.adapter.mail;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class RealSendQuizMailAdapterTest {

    @Test
    void 날짜조회성공() {
        final LocalDateTime localDateTime = LocalDateTime.parse("2022-06-05T14:11:48.612236");
        final String result = RealSendQuizMailAdapter.DateUtils.getCurrentDate(localDateTime);

        assertThat(result).isEqualTo("2022/06/05");
    }

    @Test
    public void 파일읽기성공() throws IOException {
        final String fileText = RealSendQuizMailAdapter.FileUtils.readFileText("classpath:static/mail/startMailFormat.html");
        assertThat(fileText).isNotNull();
    }

}