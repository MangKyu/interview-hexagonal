package com.mangkyu.employment.interview.app.member.domain.port.out;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.member.adapter.presentation.AddMemberRequest;

public interface SaveMemberPort {

    void save(MemberEntity memberEntity);

}
