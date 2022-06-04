package com.mangkyu.employment.interview.app.member.adapter.persistence;

import com.mangkyu.employment.interview.app.member.domain.QuizDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberPersistenceRepository extends JpaRepository<MemberEntity, Long> {

    List<MemberEntity> findAllByIsEnableTrueAndQuizDaySetIs(final QuizDay QuizDay);

}
