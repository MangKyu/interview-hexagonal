package com.mangkyu.employment.interview.app.quiz.adapter.presentation;

import com.google.gson.Gson;
import com.mangkyu.employment.interview.app.common.errors.CommonErrorCode;
import com.mangkyu.employment.interview.app.quiz.domain.QuizCategory;
import com.mangkyu.employment.interview.app.quiz.domain.QuizLevel;
import com.mangkyu.employment.interview.app.quiz.testbase.QuizTestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class AddQuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @ParameterizedTest
    @CsvSource({",JAVA,NEW", "title,,NEW", "title,JAVA,"})
    void 퀴즈추가실패_잘못된입력값(final String title, final QuizCategory quizCategory, final QuizLevel quizLevel) throws Exception {
        final List<QuizLevel> quizLevelList = quizLevel == null
                ? Collections.emptyList()
                : Collections.singletonList(quizLevel);

        final AddQuizRequest addQuizRequest = AddQuizRequest.builder()
                .title(title)
                .quizCategory(quizCategory)
                .quizLevel(quizLevelList)
                .build();

        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(addQuizRequest)));

        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("code", CommonErrorCode.INVALID_PARAMETER).exists())
                .andExpect(jsonPath("message", CommonErrorCode.INVALID_PARAMETER.getMessage()).exists());
    }

    @Test
    void 퀴즈추가성공() throws Exception {
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(QuizTestBase.addQuizRequest())));

        result.andExpect(status().isCreated());

    }

}