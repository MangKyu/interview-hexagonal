package com.mangkyu.employment.interview.app.member.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface MemberPersistenceRepository extends JpaRepository<MemberEntity, Long> {
}
