package com.mangkyu.employment.interview.app.member.testbase;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.member.adapter.presentation.AddMemberRequest;
import com.mangkyu.employment.interview.app.member.converter.MemberConverter;
import com.mangkyu.employment.interview.app.member.domain.Member;
import com.mangkyu.employment.interview.app.member.domain.QuizDay;
import com.mangkyu.employment.interview.app.quiz.domain.QuizCategory;
import com.mangkyu.employment.interview.app.quiz.domain.QuizLevel;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class MemberTestBase {

    public static final Set<QuizDay> quizDaySet = new HashSet<>(List.of(
            QuizDay.MONDAY,
            QuizDay.WEDNESDAY,
            QuizDay.FRIDAY));

    public static final Set<QuizCategory> quizCategorySet = new HashSet<>(List.of(
            QuizCategory.JAVA,
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
        return toMemberEntity(addMemberRequest);
    }

    public static Member member() {
        return MemberConverter.INSTANCE.toMember(toMemberEntity(addMemberRequest));
    }

    private static MemberEntity toMemberEntity(final AddMemberRequest addMemberRequest) {
        final MemberEntity memberEntity = MemberConverter.INSTANCE.toMemberEntity(addMemberRequest);
        ReflectionTestUtils.setField(memberEntity, "createdAt", LocalDateTime.now());
        return memberEntity;
    }

    public static List<MemberEntity> allDaysMemberEntity() {
        return Arrays.stream(QuizDay.values())
                .map(v -> toMemberEntity(createAddMemberRequest(v)))
                .collect(Collectors.toList());
    }

    private static AddMemberRequest createAddMemberRequest(final QuizDay v) {
        return AddMemberRequest.builder()
                .email(UUID.randomUUID() + "@gmail.com")
                .quizLevel(QuizLevel.JUNIOR)
                .quizDaySet(Collections.singleton(v))
                .quizCategorySet(quizCategorySet)
                .build();
    }

}
