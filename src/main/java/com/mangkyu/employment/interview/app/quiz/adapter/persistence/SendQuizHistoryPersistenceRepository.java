package com.mangkyu.employment.interview.app.quiz.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SendQuizHistoryPersistenceRepository extends JpaRepository<SendQuizHistoryEntity, Long> {

    List<SendQuizHistoryEntity> findAllByMember_Id(Long memberId);

}
