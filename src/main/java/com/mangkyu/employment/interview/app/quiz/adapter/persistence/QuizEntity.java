package com.mangkyu.employment.interview.app.quiz.adapter.persistence;

import com.mangkyu.employment.interview.app.common.adapter.persistence.BaseEntity;
import com.mangkyu.employment.interview.app.quiz.domain.QuizCategory;
import com.mangkyu.employment.interview.app.quiz.domain.QuizLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quiz")
@Getter
@SuperBuilder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class QuizEntity extends BaseEntity {

    @Column(nullable = false)
    private String resourceId;

    private String title;

    @Enumerated(EnumType.STRING)
    private QuizCategory quizCategory;

    @ElementCollection(targetClass = QuizLevel.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private List<QuizLevel> quizLevel;

}