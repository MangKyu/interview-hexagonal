package com.mangkyu.employment.interview.app.quiz.adapter.cron;

import com.mangkyu.employment.interview.app.member.domain.Member;
import com.mangkyu.employment.interview.app.member.domain.QuizDay;
import com.mangkyu.employment.interview.app.member.domain.port.out.SaveMemberPort;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.domain.QuizCategory;
import com.mangkyu.employment.interview.app.quiz.domain.QuizLevel;
import com.mangkyu.employment.interview.app.quiz.domain.port.in.SendQuizUseCase;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SaveQuizPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@RequiredArgsConstructor
@Profile("local")
@Slf4j
public class LocalSendQuizCronJob implements SendQuizCronJob {

    private final SendQuizUseCase sendQuizUseCase;
    private final SaveQuizPort saveQuizPort;
    private final SaveMemberPort saveMemberPort;

    @Scheduled(cron = "*/30 * * * * *") // every 30 seconds
    public void sendQuizMail() {
        final int result = sendQuizUseCase.sendToEveryMember();
        log.info("Send count: {}", result);
    }

    @PostConstruct
    void init() {
        final Set<QuizDay> quizDaySet = new HashSet<>();
        quizDaySet.add(QuizDay.MONDAY);
        quizDaySet.add(QuizDay.WEDNESDAY);
        quizDaySet.add(QuizDay.SATURDAY);

        final Set<QuizCategory> quizCategorySet = new HashSet<>();
        quizCategorySet.add(QuizCategory.JAVA);
        quizCategorySet.add(QuizCategory.SPRING);
        quizCategorySet.add(QuizCategory.SERVER);
        quizCategorySet.add(QuizCategory.NETWORK);
        quizCategorySet.add(QuizCategory.OPERATING_SYSTEM);
        quizCategorySet.add(QuizCategory.DATABASE);
        quizCategorySet.add(QuizCategory.PROGRAMMING);
        quizCategorySet.add(QuizCategory.DATA_STRUCTURE);
        quizCategorySet.add(QuizCategory.ALGORITHM);
        quizCategorySet.add(QuizCategory.PROBLEM_SOLVING);
        quizCategorySet.add(QuizCategory.CULTURE);
        quizCategorySet.add(QuizCategory.EXPERIENCE);
        quizCategorySet.add(QuizCategory.PERSONALITY);

        final Member member1 = Member.builder()
                .resourceId(UUID.randomUUID().toString())
                .email("whalsrb1226@naver.com")
                .quizSize(5)
                .quizLevel(QuizLevel.JUNIOR)
                .quizDaySet(quizDaySet)
                .quizCategorySet(quizCategorySet)
                .isEnable(true)
                .build();

        saveMemberPort.save(member1);

        saveQuizPort.add(quiz(QuizCategory.JAVA, Collections.singletonList(QuizLevel.JUNIOR), "Junit4 vs Junit5 차이는 무엇인가?"));
        saveQuizPort.add(quiz(QuizCategory.JAVA, Collections.singletonList(QuizLevel.JUNIOR), "Junit4 vs Junit5 차이는 무엇인가?"));
        saveQuizPort.add(quiz(QuizCategory.JAVA, Collections.singletonList(QuizLevel.JUNIOR), "Junit4 vs Junit5 차이는 무엇인가?"));
        saveQuizPort.add(quiz(QuizCategory.JAVA, Collections.singletonList(QuizLevel.JUNIOR), "Junit4 vs Junit5 차이는 무엇인가?"));
        saveQuizPort.add(quiz(QuizCategory.JAVA, Collections.singletonList(QuizLevel.JUNIOR), "Junit4 vs Junit5 차이는 무엇인가?"));
        saveQuizPort.add(quiz(QuizCategory.PROGRAMMING, Arrays.asList(QuizLevel.JUNIOR, QuizLevel.SENIOR), "DDD의 Layered Architecture에서 Presentation, Application, Domain, InfraStructure layer의 역할에 대해 설명해 주세요."));
        saveQuizPort.add(quiz(QuizCategory.SPRING, Collections.singletonList(QuizLevel.JUNIOR), "Spring Framework에서 사용되는 대표적인 디자인 패턴과 적용된 곳을 설명해주세요."));
        saveQuizPort.add(quiz(QuizCategory.DATABASE, Arrays.asList(QuizLevel.JUNIOR, QuizLevel.SENIOR), "MSA의 장점과 단점에 대해 설명해주세요"));
    }

    private QuizEntity quiz(final QuizCategory quizCategory, final List<QuizLevel> quizLevelList, final String title) {
        return QuizEntity.builder()
                .resourceId(UUID.randomUUID().toString())
                .quizCategory(quizCategory)
                .quizLevel(quizLevelList)
                .title(title).build();
    }
}
