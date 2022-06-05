package com.mangkyu.employment.interview.app.quiz.adapter.persistence;

import com.mangkyu.employment.interview.app.common.adapter.persistence.BaseEntity;
import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "solved_quiz")
@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class SendQuizHistoryEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private QuizEntity quiz;

}