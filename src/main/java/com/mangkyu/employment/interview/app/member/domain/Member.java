package com.mangkyu.employment.interview.app.member.domain;

import com.mangkyu.employment.interview.app.quiz.domain.QuizCategory;
import com.mangkyu.employment.interview.app.quiz.domain.QuizLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private long id;
    private String email;
    private QuizLevel quizLevel;
    private int quizSize;
    private Set<QuizDay> quizDaySet;
    private Set<QuizCategory> quizCategorySet;
    private boolean isEnable;

    public void disable() {
        this.isEnable = false;
    }
}
