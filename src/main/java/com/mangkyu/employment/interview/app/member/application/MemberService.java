package com.mangkyu.employment.interview.app.member.application;

import com.mangkyu.employment.interview.app.member.adapter.presentation.AddMemberRequest;
import com.mangkyu.employment.interview.app.member.converter.MemberConverter;
import com.mangkyu.employment.interview.app.member.domain.port.in.AddMemberUseCase;
import com.mangkyu.employment.interview.app.member.domain.port.out.SaveMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements AddMemberUseCase {

    private final SaveMemberPort saveMemberPort;

    @Override
    public void add(final AddMemberRequest addMemberRequest) {
        saveMemberPort.save(MemberConverter.INSTANCE.toMember(addMemberRequest));
    }
}
