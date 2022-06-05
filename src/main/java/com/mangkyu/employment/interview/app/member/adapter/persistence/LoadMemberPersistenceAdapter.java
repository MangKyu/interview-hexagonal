package com.mangkyu.employment.interview.app.member.adapter.persistence;

import com.mangkyu.employment.interview.app.member.converter.MemberConverter;
import com.mangkyu.employment.interview.app.member.domain.Member;
import com.mangkyu.employment.interview.app.member.domain.QuizDay;
import com.mangkyu.employment.interview.app.member.domain.port.out.LoadMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoadMemberPersistenceAdapter implements LoadMemberPort {

    private final MemberPersistenceRepository repository;

    @Override
    public List<Member> findEnabledMembers(final QuizDay quizDay) {
        return repository.findAllByIsEnableTrueAndQuizDaySetIs(quizDay)
                .stream()
                .map(MemberConverter.INSTANCE::toMember)
                .collect(Collectors.toList());
    }
}
