package com.mangkyu.employment.interview.app.quiz.adapter.persistence;

import com.mangkyu.employment.interview.app.member.domain.Member;
import com.mangkyu.employment.interview.app.quiz.converter.QuizConverter;
import com.mangkyu.employment.interview.app.quiz.domain.Quiz;
import com.mangkyu.employment.interview.app.quiz.domain.UnsentQuizzes;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.LoadQuizPort;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.LoadSendQuizHistoryPort;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.SaveQuizPort;
import com.mangkyu.employment.interview.app.quiz.errors.QuizErrorCode;
import com.mangkyu.employment.interview.app.quiz.errors.QuizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizPersistenceAdapter implements SaveQuizPort, LoadQuizPort {

    private final QuizPersistenceRepository quizPersistenceRepository;
    private final LoadSendQuizHistoryPort loadSendQuizHistoryPort;

    @Transactional
    public void add(final QuizEntity quizEntity) {
        quizPersistenceRepository.save(quizEntity);
    }

    @Override
    public Quiz findByResourceId(final String resourceId) {
        return QuizConverter.INSTANCE.toQuiz(quizPersistenceRepository.findByResourceId(resourceId)
                .orElseThrow(() -> new QuizException(QuizErrorCode.QUIZ_NOT_FOUND)));
    }

    @Override
    public UnsentQuizzes findUnsentQuizzes(final Member member) {
        final Set<Long> sentQuizIdSet = loadSendQuizHistoryPort.findSentQuizIdSet(member.getId());

        final List<Quiz> quizList = quizPersistenceRepository.customFindByIdNotInAndQuizCategoryInAndQuizLevel(sentQuizIdSet, member.getQuizCategorySet(), member.getQuizLevel())
                .stream()
                .map(QuizConverter.INSTANCE::toQuiz)
                .collect(Collectors.toList());

        return UnsentQuizzes.builder()
                .email(member.getEmail())
                .quizList(quizList)
                .sendSize(member.getQuizSize()).build();
    }

}
