package com.mangkyu.employment.interview.app.member.converter;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import org.junit.jupiter.api.Test;

import static com.mangkyu.employment.interview.app.member.testbase.MemberTestBase.addMemberRequest;
import static org.assertj.core.api.Assertions.assertThat;

class MemberConverterTest {

    @Test
    void 멤버엔티티로변환() {
        final MemberEntity result = MemberConverter.INSTANCE.toMemberEntity(addMemberRequest);

        assertThat(result.getResourceId()).isEqualTo(addMemberRequest.getResourceId());
        assertThat(result.getEmail()).isEqualTo(addMemberRequest.getEmail());
        assertThat(result.getQuizLevel()).isEqualTo(addMemberRequest.getQuizLevel());
        assertThat(result.getQuizDaySet()).isEqualTo(addMemberRequest.getQuizDaySet());
        assertThat(result.getQuizCategorySet()).isEqualTo(addMemberRequest.getQuizCategorySet());
        assertThat(result.getQuizSize()).isEqualTo(addMemberRequest.getQuizSize());
    }


}