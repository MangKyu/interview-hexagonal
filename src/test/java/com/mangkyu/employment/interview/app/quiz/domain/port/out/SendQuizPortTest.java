package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.quiz.adapter.mail.SendQuizMailAdapter;
import com.mangkyu.employment.interview.app.quiz.domain.UnsentQuizzes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase.quiz;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SendQuizPortTest {

    private SendQuizPort target;
    private JavaMailSender mailSender;

    private final String userEmail = "whalsrb1226@naver.com";

    @BeforeEach
    void init() throws IOException {
        mailSender = mock(JavaMailSender.class);
        target = new SendQuizMailAdapter(mailSender);

        ReflectionTestUtils.setField(target, "START_MAIL_FORMAT", "{currentDate}");
        ReflectionTestUtils.setField(target, "BODY_MAIL_FORMAT", "{quiz.title}, {quiz.category}, {quiz.level}");
        ReflectionTestUtils.setField(target, "END_MAIL_FORMAT", "end mail format");
    }

    @Test
    public void 메일발송_보낼퀴즈가없음() {
        final UnsentQuizzes unsentQuizzes = UnsentQuizzes.builder()
                .email(userEmail)
                .quizList(Collections.emptyList())
                .sendSize(3)
                .build();

        final int result = target.send(unsentQuizzes);

        // then
        assertThat(result).isZero();
    }

    @Test
    public void 메일발송_예외발생() {
        final UnsentQuizzes unsentQuizzes = UnsentQuizzes.builder()
                .email(userEmail)
                .quizList(List.of(quiz(), quiz(), quiz()))
                .sendSize(3)
                .build();

        final MimeMessage message = new MimeMessage(Session.getInstance(new Properties()));
        doReturn(message)
                .when(mailSender)
                .createMimeMessage();

        doThrow(new MailSendException("Fail"))
                .when(mailSender)
                .send(message);

        final int result = target.send(unsentQuizzes);

        // then
        assertThat(result).isZero();
    }

    @Test
    public void 메일발송_마지막문제() {
        final UnsentQuizzes unsentQuizzes = UnsentQuizzes.builder()
                .email(userEmail)
                .quizList(List.of(quiz(), quiz(), quiz()))
                .sendSize(3)
                .build();

        doReturn(new MimeMessage(Session.getInstance(new Properties())))
                .when(mailSender)
                .createMimeMessage();

        final int result = target.send(unsentQuizzes);

        assertThat(result).isOne();
    }

    @Test
    public void 메일발송_마지막문제가아님() {
        final UnsentQuizzes unsentQuizzes = UnsentQuizzes.builder()
                .email(userEmail)
                .quizList(List.of(quiz(), quiz(), quiz(), quiz()))
                .sendSize(3)
                .build();

        doReturn(new MimeMessage(Session.getInstance(new Properties())))
                .when(mailSender)
                .createMimeMessage();

        target.send(unsentQuizzes);
    }

}