package com.mangkyu.employment.interview.app.quiz.errors;

import com.mangkyu.employment.interview.app.common.errors.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum QuizErrorCode implements ErrorCode {

    QUIZ_NOT_FOUND(HttpStatus.NOT_FOUND, "Quiz is not found"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}

