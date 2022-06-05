package com.mangkyu.employment.interview.app.member.domain.port.out;

import com.mangkyu.employment.interview.app.member.domain.Member;

public interface SaveMemberPort {

    void save(Member member);

}
