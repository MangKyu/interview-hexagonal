package com.mangkyu.employment.interview.app.member.domain.port.out;

import com.mangkyu.employment.interview.app.member.adapter.persistence.LoadMemberPersistenceAdapter;
import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberPersistenceRepository;
import com.mangkyu.employment.interview.app.member.domain.Member;
import com.mangkyu.employment.interview.app.member.domain.QuizDay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.mangkyu.employment.interview.app.member.testbase.MemberTestBase.allDaysMemberEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LoadMemberPortTest {

    private LoadMemberPort target;

    @Autowired
    private MemberPersistenceRepository memberPersistenceRepository;

    @BeforeEach
    void init() {
        target = new LoadMemberPersistenceAdapter(memberPersistenceRepository);
    }

    @Test
    void 특정날짜에활성화된구성원찾기() {
        memberPersistenceRepository.saveAll(allDaysMemberEntity());

        final List<Member> result = target.findEnabledMembers(QuizDay.FRIDAY);

        assertThat(result.size()).isNotZero();
    }

}