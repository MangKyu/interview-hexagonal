package com.mangkyu.employment.interview.app.quiz.adapter.persistence;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SaveSendQuizHistoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SendQuizHistoryHistoryPersistenceAdapter implements SaveSendQuizHistoryPort {

    private final SendQuizHistoryPersistenceRepository repository;

    @Override
    @Transactional
    public void save(final MemberEntity memberEntity, final QuizEntity quizEntity) {
        repository.save(new SendQuizHistoryEntity(memberEntity, quizEntity));
    }

}
