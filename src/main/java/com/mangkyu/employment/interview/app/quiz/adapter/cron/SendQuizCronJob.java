package com.mangkyu.employment.interview.app.quiz.adapter.cron;

import com.mangkyu.employment.interview.app.quiz.domain.port.in.SendQuizUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("!local")
@Slf4j
public class SendQuizCronJob {

    private final SendQuizUseCase sendQuizUseCase;

    @Scheduled(cron = "0 1 0 * * ?")
    public void sendQuizMail() {
        final int result = sendQuizUseCase.sendToEveryMember();
        log.info("Send count: {}", result);
    }

}
