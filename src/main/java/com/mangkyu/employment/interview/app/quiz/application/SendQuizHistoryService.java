package com.mangkyu.employment.interview.app.quiz.application;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.SendQuizHistoryPersistenceRepository;
import com.mangkyu.employment.interview.app.quiz.domain.port.out.LoadSendQuizHistoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SendQuizHistoryService implements LoadSendQuizHistoryPort {

    private final SendQuizHistoryPersistenceRepository repository;

    @Override
    public Set<Long> findSentQuizIdSet(final Long memberId) {
        return repository.findAllByMember_Id(memberId)
                .stream()
                .map(v -> v.getQuiz().getId())
                .collect(Collectors.toSet());
    }

}
