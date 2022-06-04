package com.mangkyu.employment.interview.app.member.testbase;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.member.adapter.presentation.AddMemberRequest;
import com.mangkyu.employment.interview.app.member.converter.MemberConverter;
import com.mangkyu.employment.interview.app.member.domain.QuizDay;
import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.converter.QuizConverter;
import com.mangkyu.employment.interview.app.quiz.domain.QuizCategory;
import com.mangkyu.employment.interview.app.quiz.domain.QuizLevel;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemberTestBase {

    public static final Set<QuizDay> quizDaySet = new HashSet<>(List.of(
            QuizDay.MONDAY,
            QuizDay.WEDNESDAY,
            QuizDay.FRIDAY));

    public static final Set<QuizCategory> quizCategorySet = new HashSet<>(List.of(
            QuizCategory.CULTURE,
            QuizCategory.DATABASE,
            QuizCategory.EXPERIENCE));

    public static final AddMemberRequest addMemberRequest = AddMemberRequest.builder()
            .email("whalsrb1226@gmail.com")
            .quizLevel(QuizLevel.JUNIOR)
            .quizDaySet(quizDaySet)
            .quizCategorySet(quizCategorySet)
            .build();

    public static MemberEntity memberEntity() {
        final MemberEntity memberEntity = MemberConverter.INSTANCE.toMemberEntity(addMemberRequest);
        ReflectionTestUtils.setField(memberEntity, "createdAt", LocalDateTime.now());
        return memberEntity;
    }

}
