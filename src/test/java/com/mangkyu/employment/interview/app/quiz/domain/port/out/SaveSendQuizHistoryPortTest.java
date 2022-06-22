package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberPersistenceRepository;
import com.mangkyu.employment.interview.app.member.converter.MemberConverter;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizPersistenceRepository;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.SaveSendQuizHistoryHistoryPersistenceAdapter;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.SendQuizHistoryPersistenceRepository;
import com.mangkyu.employment.interview.app.quiz.converter.QuizConverter;
import com.mangkyu.employment.interview.app.quiz.domain.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Collectors;

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
        target = new SaveSendQuizHistoryHistoryPersistenceAdapter(solvedQuizPersistenceRepository);
    }

    @Test
    void 해결한문제들일괄추가() {
        final MemberEntity memberEntity = memberEntity();
        memberPersistenceRepository.save(memberEntity);

        final List<Quiz> quizList = quizPersistenceRepository.saveAll(List.of(quizEntity(), quizEntity()))
                .stream().map(QuizConverter.INSTANCE::toQuiz).collect(Collectors.toList());


        target.saveAll(MemberConverter.INSTANCE.toMember(memberEntity), quizList);

        assertThat(solvedQuizPersistenceRepository.count()).isEqualTo(2);
    }

}