package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberPersistenceRepository;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizPersistenceRepository;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.SendQuizHistoryEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.SendQuizHistoryPersistenceRepository;
import com.mangkyu.employment.interview.app.quiz.application.SendQuizHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static com.mangkyu.employment.interview.app.member.testbase.MemberTestBase.memberEntity;
import static com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase.quizEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LoadSendQuizHistoryPortTest {

    private LoadSendQuizHistoryPort target;

    @Autowired
    private SendQuizHistoryPersistenceRepository repository;

    @Autowired
    private QuizPersistenceRepository quizPersistenceRepository;

    @Autowired
    private MemberPersistenceRepository memberPersistenceRepository;

    @BeforeEach
    void init() {
        target = new SendQuizHistoryService(repository);
    }

    @Test
    void 퀴즈발송내역조회() {
        final MemberEntity memberEntity = memberPersistenceRepository.save(memberEntity());
        final QuizEntity quizEntity = quizPersistenceRepository.save(quizEntity());

        repository.save(new SendQuizHistoryEntity(memberEntity, quizEntity));

        final Set<Long> sendQuizIdSet = target.findSentQuizIdSet(memberEntity.getId());
        assertThat(sendQuizIdSet.isEmpty()).isFalse();
    }

}