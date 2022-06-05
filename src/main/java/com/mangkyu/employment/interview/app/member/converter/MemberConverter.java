package com.mangkyu.employment.interview.app.member.converter;

import com.mangkyu.employment.interview.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.employment.interview.app.member.adapter.presentation.AddMemberRequest;
import com.mangkyu.employment.interview.app.member.domain.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberConverter {

    MemberConverter INSTANCE = Mappers.getMapper(MemberConverter.class);

    MemberEntity toMemberEntity(final AddMemberRequest addMemberRequest);

    MemberEntity toMemberEntity(final Member member);

    Member toMember(final AddMemberRequest addMemberRequest);

    Member toMember(final MemberEntity memberEntity);

}
