package com.mangkyu.employment.interview.app.solvedquiz.domain.port.out;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberPersistenceRepository;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizPersistenceRepository;
import com.mangkyu.employment.interview.app.solvedquiz.adapter.persistence.SolvedQuizPersistenceAdapter;
import com.mangkyu.employment.interview.app.solvedquiz.adapter.persistence.SolvedQuizPersistenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.mangkyu.employment.interview.app.member.testbase.MemberTestBase.memberEntity;
import static com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase.quizEntity;
import static com.mangkyu.employment.interview.app.solvedquiz.testbase.SolvedQuizTestBase.solvedQuizEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SaveSolvedQuizPortTest {

    private SaveSolvedQuizPort target;

    @Autowired
    private SolvedQuizPersistenceRepository solvedQuizPersistenceRepository;

    @Autowired
    private MemberPersistenceRepository memberPersistenceRepository;
    @Autowired
    private QuizPersistenceRepository quizPersistenceRepository;

    @BeforeEach
    void init() {
        target = new SolvedQuizPersistenceAdapter(solvedQuizPersistenceRepository);
    }

    @Test
    void 해결한문제추가() {
        final MemberEntity memberEntity = memberEntity();
        memberPersistenceRepository.save(memberEntity);

        final QuizEntity quizEntity = quizEntity();
        quizPersistenceRepository.save(quizEntity);


        target.save(solvedQuizEntity(memberEntity, quizEntity));

        assertThat(solvedQuizPersistenceRepository.count()).isOne();
    }

}