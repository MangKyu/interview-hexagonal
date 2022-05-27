package com.mangkyu.employment.interview.app.quiz.adapter.presentation;

import com.mangkyu.employment.interview.app.quiz.domain.port.in.GetQuizUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class GetQuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GetQuizUseCase getQuizUseCase;

    @Test
    void 퀴즈조회성공() throws Exception {
        doReturn(getQuizResponse())
                .when(getQuizUseCase)
                .getQuiz(quizResourceId);

        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/quizzes/{id}", quizResourceId));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("title").value(title));
    }

}