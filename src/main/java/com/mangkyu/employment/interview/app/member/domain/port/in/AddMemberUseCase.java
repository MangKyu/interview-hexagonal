package com.mangkyu.employment.interview.app.member.domain.port.in;

import com.mangkyu.employment.interview.app.member.adapter.presentation.AddMemberRequest;

public interface AddMemberUseCase {
    void add(AddMemberRequest addMemberRequest);
}
