package com.mangkyu.employment.interview.app.quiz.adapter.persistence;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.member.converter.MemberConverter;
import com.mangkyu.employment.interview.app.member.domain.Member;
import com.mangkyu.employment.interview.app.quiz.converter.QuizConverter;
import com.mangkyu.employment.interview.app.quiz.domain.Quiz;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SaveSendQuizHistoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class SaveSendQuizHistoryHistoryPersistenceAdapter implements SaveSendQuizHistoryPort {

    private final SendQuizHistoryPersistenceRepository repository;

    @Override
    public void saveAll(final Member member, final List<Quiz> quizList) {
        final MemberEntity memberEntity = MemberConverter.INSTANCE.toMemberEntity(member);
        final List<QuizEntity> quizEntityList = QuizConverter.INSTANCE.toQuizEntities(quizList);
        for (final QuizEntity quizEntity : quizEntityList) {
            this.save(memberEntity, quizEntity);
        }
    }

    private void save(final MemberEntity memberEntity, final QuizEntity quizEntity) {
        repository.save(new SendQuizHistoryEntity(memberEntity, quizEntity));
    }

}
