package com.mangkyu.employment.interview.app.quiz.adapter.presentation;

import com.mangkyu.employment.interview.app.quiz.domain.port.in.GetQuizUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class GetQuizController {

    private final GetQuizUseCase getQuizUseCase;

    @GetMapping("/quizzes/{resourceId}")
    public ResponseEntity<GetQuizResponse> getQuiz(@PathVariable String resourceId) {
        return ResponseEntity.ok(getQuizUseCase.getQuiz(resourceId));
    }

}