package com.mangkyu.employment.interview.app.member.adapter.persistence;

import com.mangkyu.employment.interview.app.common.adapter.persistence.BaseEntity;
import com.mangkyu.employment.interview.app.member.domain.QuizDay;
import com.mangkyu.employment.interview.app.quiz.domain.QuizCategory;
import com.mangkyu.employment.interview.app.quiz.domain.QuizLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "member")
@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class MemberEntity extends BaseEntity {

    @Column(nullable = false)
    private String resourceId;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private QuizLevel quizLevel;

    @ElementCollection(targetClass = QuizDay.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<QuizDay> quizDaySet;

    @ElementCollection(targetClass = QuizCategory.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<QuizCategory> quizCategorySet;

    @Builder.Default
    private Integer quizSize = 3;

}