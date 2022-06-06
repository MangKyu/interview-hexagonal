package com.mangkyu.employment.interview.app.quiz.adapter.mail;

import com.mangkyu.employment.interview.app.quiz.domain.Quiz;
import com.mangkyu.employment.interview.app.quiz.domain.UnsentQuizzes;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SendQuizPort;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
public class SendQuizMailAdapter implements SendQuizPort {

    private final JavaMailSender mailSender;
    private final String START_MAIL_FORMAT;
    private final String BODY_MAIL_FORMAT;
    private final String END_MAIL_FORMAT;

    public SendQuizMailAdapter(final JavaMailSender mailSender) throws IOException {
        this.mailSender = mailSender;
        START_MAIL_FORMAT = FileUtils.readFileText("classpath:static/mail/startMailFormat.html");
        BODY_MAIL_FORMAT = FileUtils.readFileText("classpath:static/mail/bodyMailFormat.html");
        END_MAIL_FORMAT = FileUtils.readFileText("classpath:static/mail/endMailFormat.html");
    }

    public int send(final UnsentQuizzes unsentQuizzes) {
        if (unsentQuizzes.isEmpty()) {
            return 0;
        }

        try {
            final MimeMessage message = mailSender.createMimeMessage();
            final MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(unsentQuizzes.getEmail());
            messageHelper.setSubject(createSubjectText(unsentQuizzes.isLast()));

            final String mailText = createMailText(unsentQuizzes.getRandomQuizUnderSize());
            messageHelper.setText(mailText, true);
            mailSender.send(message);
        } catch (final Exception e) {
            log.error("send mail fail: {}", unsentQuizzes.getEmail(), e);
            return 0;
        }

        return 1;
    }

    private String createSubjectText(final boolean isLastMail) {
        final String subjectText = DateUtils.getCurrentDate(LocalDateTime.now()) + " 망나니개발자 기술면접 질문입니다.";
        return isLastMail
                ? subjectText + " (마지막 문제입니다.)"
                : subjectText;
    }

    private String createMailText(final List<Quiz> quizList) {
        return createStartMailText() +
                createBodyMailText(quizList) +
                END_MAIL_FORMAT;
    }

    private String createStartMailText() {
        return START_MAIL_FORMAT.replaceAll("\\$\\{currentDate}", DateUtils.getCurrentDate(LocalDateTime.now()));
    }

    private String createBodyMailText(final List<Quiz> quizList) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (Quiz quiz : quizList) {
            final String indexReplacedText = BODY_MAIL_FORMAT.replaceAll("\\{index}", String.valueOf(quizList.indexOf(quiz) + 1));
            final String titleReplacedText = indexReplacedText.replaceAll("\\$\\{quiz.title}", quiz.getTitle());
            final String categoryReplacedText = titleReplacedText.replaceAll("\\$\\{quiz.category}", quiz.getQuizCategory().name());
            final String finalText = categoryReplacedText.replaceAll("\\$\\{quiz.level}", quiz.getQuizLevel().toString());
            stringBuilder.append(finalText);
        }

        return stringBuilder.toString();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class FileUtils {

        public static String readFileText(final String filePath) throws IOException {
            final File file = ResourceUtils.getFile(filePath);
            final InputStream inputStream = new FileInputStream(file);
            final byte[] byteData = FileCopyUtils.copyToByteArray(inputStream);
            return new String(byteData, StandardCharsets.UTF_8);
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class DateUtils {

        public static String getCurrentDate(final LocalDateTime localDateTime) {
            final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            return dateTimeFormatter.format(localDateTime);
        }

    }

}
