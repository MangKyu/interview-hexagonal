package com.mangkyu.employment.interview.app.quiz.domain.port.out;

import java.util.Set;

public interface LoadSendQuizHistoryPort {

    Set<Long> findSentQuizIdSet(Long memberId);
}
