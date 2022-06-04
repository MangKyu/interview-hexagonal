package com.mangkyu.employment.interview.app.member.adapter.presentation;

import com.google.gson.Gson;
import com.mangkyu.employment.interview.app.common.errors.CommonErrorCode;
import com.mangkyu.employment.interview.app.member.domain.QuizDay;
import com.mangkyu.employment.interview.app.quiz.domain.QuizCategory;
import com.mangkyu.employment.interview.app.quiz.domain.QuizLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Set;

import static com.mangkyu.employment.interview.app.member.testbase.MemberTestBase.addMemberRequest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class AddMemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    void 멤버추가성공() throws Exception {
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(addMemberRequest)));

        result.andExpect(status().isCreated());
    }

    @ParameterizedTest
    @CsvSource({",NEW,MONDAY,JAVA", "test,NEW,MONDAY,JAVA", "test@email.com,,MONDAY,JAVA", "test@email.com,NEW,,JAVA", "test@email.com,NEW,MONDAY,"})
    void 멤버추가실패(String email, QuizLevel quizLevel, QuizDay quizDay, QuizCategory quizCategory) throws Exception {
        Set<QuizDay> quizDaySet = new HashSet<>();
        if (quizDay != null) {
            quizDaySet.add(quizDay);
        }

        Set<QuizCategory> quizCategorySet = new HashSet<>();
        if (quizCategory != null) {
            quizCategorySet.add(quizCategory);
        }

        final AddMemberRequest request = AddMemberRequest.builder()
                .email(email)
                .quizLevel(quizLevel)
                .quizDaySet(quizDaySet)
                .quizCategorySet(quizCategorySet)
                .build();

        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request)));

        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("code", CommonErrorCode.INVALID_PARAMETER).exists())
                .andExpect(jsonPath("message", CommonErrorCode.INVALID_PARAMETER.getMessage()).exists());
    }

}