package com.mangkyu.employment.interview.app.member.adapter.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberPersistenceRepositoryTest {

    @Autowired
    private MemberPersistenceRepository target;

}