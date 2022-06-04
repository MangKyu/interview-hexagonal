package com.mangkyu.employment.interview.app.member.adapter.presentation;

import com.mangkyu.employment.interview.app.member.domain.port.in.AddMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
class AddMemberController {

    private final AddMemberUseCase addMemberUseCase;

    @PostMapping
    public ResponseEntity<Void> addMember(@RequestBody @Valid final AddMemberRequest addMemberRequest) {
        addMemberUseCase.add(addMemberRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

}
