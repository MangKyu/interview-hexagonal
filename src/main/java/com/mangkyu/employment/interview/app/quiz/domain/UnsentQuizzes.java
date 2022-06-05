package com.mangkyu.employment.interview.app.quiz.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class UnsentQuizzes {

    private final List<Quiz> quizList;
    private final int sendSize;

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

}
