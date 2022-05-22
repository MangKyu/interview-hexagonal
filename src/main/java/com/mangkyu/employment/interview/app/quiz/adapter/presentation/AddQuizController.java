package com.mangkyu.employment.interview.app.quiz.adapter.presentation;

import com.mangkyu.employment.interview.app.quiz.domain.port.in.AddQuizUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class AddQuizController {

    private final AddQuizUseCase addQuizUseCase;

    @PostMapping("/quizzes")
    public ResponseEntity<Void> addQuiz(@RequestBody @Valid final AddQuizRequest addQuizRequest) {
        addQuizUseCase.add(addQuizRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

}
