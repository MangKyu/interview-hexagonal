package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberPersistenceRepository;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.mangkyu.employment.interview.app.member.testbase.MemberTestBase.memberEntity;
import static com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase.quizEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SaveSendQuizHistoryPortTest {

    private SaveSendQuizHistoryPort target;

    @Autowired
    private SendQuizHistoryPersistenceRepository solvedQuizPersistenceRepository;

    @Autowired
    private MemberPersistenceRepository memberPersistenceRepository;
    @Autowired
    private QuizPersistenceRepository quizPersistenceRepository;

    @BeforeEach
    void init() {
        target = new SendQuizHistoryHistoryPersistenceAdapter(solvedQuizPersistenceRepository);
    }

    @Test
    void 해결한문제추가() {
        final MemberEntity memberEntity = memberEntity();
        memberPersistenceRepository.save(memberEntity);

        final QuizEntity quizEntity = quizEntity();
        quizPersistenceRepository.save(quizEntity);


        target.save(memberEntity, quizEntity);

        assertThat(solvedQuizPersistenceRepository.count()).isOne();
    }

    @Test
    void 해결한문제들일괄추가() {
        final MemberEntity memberEntity = memberEntity();
        memberPersistenceRepository.save(memberEntity);

        final List<QuizEntity> savedQuizEntityList = quizPersistenceRepository.saveAll(List.of(quizEntity(), quizEntity()));


        target.saveAll(memberEntity, savedQuizEntityList);

        assertThat(solvedQuizPersistenceRepository.count()).isEqualTo(2);
    }

}