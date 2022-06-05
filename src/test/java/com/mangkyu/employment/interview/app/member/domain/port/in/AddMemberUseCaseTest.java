package com.mangkyu.employment.interview.app.member.domain.port.in;

import com.mangkyu.employment.interview.app.member.application.MemberService;
import com.mangkyu.employment.interview.app.member.domain.Member;
import com.mangkyu.employment.interview.app.member.domain.port.out.SaveMemberPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.mangkyu.employment.interview.app.member.testbase.MemberTestBase.addMemberRequest;
import static org.mockito.Mockito.*;

class AddMemberUseCaseTest {

    private AddMemberUseCase target;
    private SaveMemberPort saveMemberPort;

    @BeforeEach
    void init() {
        saveMemberPort = mock(SaveMemberPort.class);
        target = new MemberService(saveMemberPort);
    }

    @Test
    void 구성원추가() {
        target.add(addMemberRequest);

        verify(saveMemberPort, times(1)).save(any(Member.class));
    }

}