package com.mangkyu.employment.interview.app.quiz.domain.port.in;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.member.domain.Member;
import com.mangkyu.employment.interview.app.member.domain.QuizDay;
import com.mangkyu.employment.interview.app.member.domain.port.out.LoadMemberPort;
import com.mangkyu.employment.interview.app.member.domain.port.out.SaveMemberPort;
import com.mangkyu.employment.interview.app.quiz.application.SendQuizService;
import com.mangkyu.employment.interview.app.quiz.domain.UnsentQuizzes;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.LoadQuizPort;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SaveSendQuizHistoryPort;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SendQuizPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.mangkyu.employment.interview.app.member.testbase.MemberTestBase.member;
import static com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase.quiz;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class SendQuizUseCaseTest {

    private SendQuizUseCase target;

    private Member member;
    private List<Member> memberList;
    private QuizDay quizDay;
    private LoadMemberPort loadMemberPort;
    private LoadQuizPort loadQuizPort;
    private SaveMemberPort saveMemberPort;
    private SendQuizPort sendQuizPort;
    private SaveSendQuizHistoryPort sendQuizHistoryPort;

    @BeforeEach
    void init() {
        loadMemberPort = mock(LoadMemberPort.class);
        loadQuizPort = mock(LoadQuizPort.class);
        saveMemberPort = mock(SaveMemberPort.class);
        sendQuizPort = mock(SendQuizPort.class);
        sendQuizHistoryPort = mock(SaveSendQuizHistoryPort.class);

        target = new SendQuizService(
                loadMemberPort,
                loadQuizPort,
                saveMemberPort,
                sendQuizPort,
                sendQuizHistoryPort);

        member = member();
        memberList = Collections.singletonList(member);
        quizDay = QuizDay.findQuizDay(LocalDateTime.now());
    }

    @Test
    public void 메일전송_멤버가없음() {
        // given
        doReturn(Collections.emptyList())
                .when(loadMemberPort)
                .findEnabledMembers(quizDay);

        // when
        target.sendToEveryMember();

        // then

        // verify
        verify(loadQuizPort, times(0)).findUnsentQuizzes(member);
        verify(saveMemberPort, times(0)).save(member);
        verify(sendQuizPort, times(0)).send(any(UnsentQuizzes.class));
        verify(sendQuizHistoryPort, times(0)).saveAll(any(MemberEntity.class), anyList());
    }

    @Test
    public void 메일전송_마지막문제가아님() {
        final UnsentQuizzes unsentQuizzes = UnsentQuizzes.builder()
                .email(member.getEmail())
                .quizList(List.of(quiz(), quiz(), quiz()))
                .sendSize(2)
                .build();

        doReturn(memberList)
                .when(loadMemberPort)
                .findEnabledMembers(quizDay);

        doReturn(unsentQuizzes)
                .when(loadQuizPort)
                .findUnsentQuizzes(member);

        target.sendToEveryMember();

        verify(loadQuizPort, times(1)).findUnsentQuizzes(member);
        verify(saveMemberPort, times(0)).save(member);
        verify(sendQuizPort, times(1)).send(unsentQuizzes);
        verify(sendQuizHistoryPort, times(1)).saveAll(any(MemberEntity.class), anyList());
    }

    @Test
    public void 메일전송_마지막문제() {
        final UnsentQuizzes unsentQuizzes = UnsentQuizzes.builder()
                .email(member.getEmail())
                .quizList(List.of(quiz(), quiz(), quiz()))
                .sendSize(3)
                .build();

        doReturn(memberList)
                .when(loadMemberPort)
                .findEnabledMembers(quizDay);

        doReturn(unsentQuizzes)
                .when(loadQuizPort)
                .findUnsentQuizzes(member);

        target.sendToEveryMember();

        verify(loadQuizPort, times(1)).findUnsentQuizzes(member);
        verify(saveMemberPort, times(1)).save(member);
        verify(sendQuizPort, times(1)).send(unsentQuizzes);
        verify(sendQuizHistoryPort, times(1)).saveAll(any(MemberEntity.class), anyList());
    }

}