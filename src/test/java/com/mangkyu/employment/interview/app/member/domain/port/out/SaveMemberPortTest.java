package com.mangkyu.employment.interview.app.member.domain.port.out;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberPersistenceAdapter;
import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberPersistenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.mangkyu.employment.interview.app.member.testbase.MemberTestBase.memberEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SaveMemberPortTest {

    private SaveMemberPort target;

    @Autowired
    private MemberPersistenceRepository memberPersistenceRepository;

    @BeforeEach
    void init() {
        target = new MemberPersistenceAdapter(memberPersistenceRepository);
    }

    @Test
    void 구성원추가() {
        target.save(memberEntity());

        assertThat(memberPersistenceRepository.count()).isOne();
    }

}