package com.mangkyu.employment.interview.app.member.domain.port.out;

import com.mangkyu.employment.interview.app.member.domain.Member;
import com.mangkyu.employment.interview.app.member.domain.QuizDay;

import java.util.List;

public interface LoadMemberPort {

    List<Member> findEnabledMembers(final QuizDay friday);

}
