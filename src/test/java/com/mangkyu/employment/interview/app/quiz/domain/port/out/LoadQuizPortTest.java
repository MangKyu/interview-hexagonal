package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import com.mangkyu.employment.interview.app.common.adapter.persistence.BaseEntity;
import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberPersistenceRepository;
import com.mangkyu.employment.interview.app.member.converter.MemberConverter;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizPersistenceAdapter;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizPersistenceRepository;
import com.mangkyu.employment.interview.app.quiz.domain.Quiz;
import com.mangkyu.employment.interview.app.quiz.domain.UnsentQuizzes;
import com.mangkyu.employment.interview.app.quiz.errors.QuizErrorCode;
import com.mangkyu.employment.interview.app.quiz.errors.QuizException;
import com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mangkyu.employment.interview.app.member.testbase.MemberTestBase.memberEntity;
import static com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase.quizEntityList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@DataJpaTest
class LoadQuizPortTest {

    private LoadQuizPort target;

    @Autowired
    private QuizPersistenceRepository repository;

    @Autowired
    private MemberPersistenceRepository memberPersistenceRepository;
    private LoadSendQuizHistoryPort loadSendQuizHistoryPort;

    @BeforeEach
    void init() {
        loadSendQuizHistoryPort = mock(LoadSendQuizHistoryPort.class);
        target = new QuizPersistenceAdapter(repository, loadSendQuizHistoryPort);
    }

    @Test
    void 리소스아이디로퀴즈조회() {
        final QuizEntity quiz = QuizTestBase.quizEntity();
        repository.save(quiz);

        final Quiz result = target.findByResourceId(quiz.getResourceId());

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(quiz.getTitle());
    }

    @Test
    void 리소스아이디로퀴즈조회_퀴즈없음() {
        final QuizEntity quiz = QuizTestBase.quizEntity();

        final QuizException result = assertThrows(QuizException.class, () -> target.findByResourceId(quiz.getResourceId()));

        assertThat(result.getErrorCode()).isEqualTo(QuizErrorCode.QUIZ_NOT_FOUND);
    }

    @Test
    void 전송하지않은퀴즈목록조회_전송된게없음() {
        final MemberEntity savedMemberEntity = memberPersistenceRepository.save(memberEntity());
        repository.saveAll(quizEntityList());

        doReturn(new HashSet<>())
                .when(loadSendQuizHistoryPort)
                .findSentQuizIdSet(savedMemberEntity.getId());

        final UnsentQuizzes result = target.findUnsentQuizzes(MemberConverter.INSTANCE.toMember(savedMemberEntity));

        assertThat(result.size()).isNotZero();
    }

    @Test
    void 전송하지않은퀴즈목록조회_전송된게있음() {
        final MemberEntity savedMemberEntity = memberPersistenceRepository.save(memberEntity());
        final List<QuizEntity> savedQuizEntityList = repository.saveAll(quizEntityList());

        final Set<Long> sentQuizIdSet = savedQuizEntityList.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toSet());

        doReturn(sentQuizIdSet)
                .when(loadSendQuizHistoryPort)
                .findSentQuizIdSet(savedMemberEntity.getId());

        final UnsentQuizzes result = target.findUnsentQuizzes(MemberConverter.INSTANCE.toMember(savedMemberEntity));

        assertThat(result.size()).isZero();
    }

}