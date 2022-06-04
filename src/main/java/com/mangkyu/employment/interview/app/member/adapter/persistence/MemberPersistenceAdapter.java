package com.mangkyu.employment.interview.app.member.adapter.persistence;

import com.mangkyu.employment.interview.app.member.domain.port.out.SaveMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements SaveMemberPort {

    private final MemberPersistenceRepository memberPersistenceRepository;

    @Override
    public void save(final MemberEntity memberEntity) {
        memberPersistenceRepository.save(memberEntity);
    }

}
