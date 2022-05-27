package com.mangkyu.employment.interview.app.quiz.errors;

import com.mangkyu.employment.interview.app.common.errors.BaseException;
import lombok.Getter;

@Getter
public class QuizException extends BaseException {

    public QuizException(final QuizErrorCode errorCode) {
        super(errorCode);
    }

}