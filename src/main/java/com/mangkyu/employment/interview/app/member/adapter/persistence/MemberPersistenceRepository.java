package com.mangkyu.employment.interview.app.member.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPersistenceRepository extends JpaRepository<MemberEntity, Long> {
}
