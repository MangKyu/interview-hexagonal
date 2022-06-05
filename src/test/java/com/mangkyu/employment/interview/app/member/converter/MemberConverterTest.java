package com.mangkyu.employment.interview.app.member.converter;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.mangkyu.employment.interview.app.member.testbase.MemberTestBase.addMemberRequest;
import static com.mangkyu.employment.interview.app.member.testbase.MemberTestBase.memberEntity;
import static org.assertj.core.api.Assertions.assertThat;

class MemberConverterTest {

    @Test
    void AddMemberRequest에서Member로변환() {
        final Member result = MemberConverter.INSTANCE.toMember(addMemberRequest);

        assertThat(result.getResourceId()).isEqualTo(addMemberRequest.getResourceId());
        assertThat(result.getEmail()).isEqualTo(addMemberRequest.getEmail());
        assertThat(result.getQuizLevel()).isEqualTo(addMemberRequest.getQuizLevel());
        assertThat(result.getQuizDaySet()).isEqualTo(addMemberRequest.getQuizDaySet());
        assertThat(result.getQuizCategorySet()).isEqualTo(addMemberRequest.getQuizCategorySet());
        assertThat(result.getQuizSize()).isEqualTo(addMemberRequest.getQuizSize());
    }

    @Test
    void MemberEntity에서Member로변환() {
        final MemberEntity memberEntity = memberEntity();
        ReflectionTestUtils.setField(memberEntity, "id", 1L);

        final Member result = MemberConverter.INSTANCE.toMember(memberEntity);

        assertThat(result.getId()).isEqualTo(memberEntity.getId());
        assertThat(result.getEmail()).isEqualTo(memberEntity.getEmail());
        assertThat(result.getQuizLevel()).isEqualTo(memberEntity.getQuizLevel());
        assertThat(result.getQuizSize()).isEqualTo(memberEntity.getQuizSize());
        assertThat(result.getQuizDaySet()).isEqualTo(memberEntity.getQuizDaySet());
        assertThat(result.getQuizCategorySet()).isEqualTo(memberEntity.getQuizCategorySet());
        assertThat(result.isEnable()).isTrue();
    }

}