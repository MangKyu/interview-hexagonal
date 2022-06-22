package com.mangkyu.employment.interview.app.quiz.adapter.mail;

import com.mangkyu.employment.interview.app.quiz.domain.UnsentQuizzes;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SendQuizPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
@Slf4j
public class LocalSendQuizMailAdapter implements SendQuizPort {

    public int send(final UnsentQuizzes unsentQuizzes) {
        return 1;
    }

}
