package com.mangkyu.employment.interview.app.quiz.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Builder
@RequiredArgsConstructor
public class UnsentQuizzes {

    @Getter
    private final String email;
    @Getter
    private final int sendSize;

    private final List<Quiz> quizList;

    public int size() {
        return quizList.size();
    }

    public boolean isLast() {
        return quizList.size() <= sendSize;
    }

    public List<Quiz> getRandomQuizUnderSize() {
        final List<Quiz> copiedQuizList = new ArrayList<>(quizList);
        if (isLast()) {
            return copiedQuizList;
        }

        Collections.shuffle(copiedQuizList);
        return copiedQuizList.subList(0, sendSize);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

}
