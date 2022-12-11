package com.mangkyu.employment.interview.app.member.adapter.persistence;

import com.mangkyu.employment.interview.app.member.converter.MemberConverter;
import com.mangkyu.employment.interview.app.member.domain.Member;
import com.mangkyu.employment.interview.app.member.domain.port.out.SaveMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class SaveMemberPersistenceAdapter implements SaveMemberPort {

    private final MemberPersistenceRepository memberPersistenceRepository;

    @Override
    public void save(final Member member) {
        memberPersistenceRepository.save(MemberConverter.INSTANCE.toMemberEntity(member));
    }

}
