package com.mangkyu.employment.interview.app.quiz.converter;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.AddQuizRequest;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.GetQuizResponse;
import com.mangkyu.employment.interview.app.quiz.domain.Quiz;
import com.mangkyu.employment.interview.app.quiz.domain.QuizLevel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface QuizConverter {

    QuizConverter INSTANCE = Mappers.getMapper(QuizConverter.class);

    QuizEntity toQuizEntity(final AddQuizRequest addQuizRequest);

    Quiz toQuiz(final QuizEntity quizEntity);

    default GetQuizResponse toGetQuizResponse(final Quiz quiz) {
        return GetQuizResponse.builder()
                .title(quiz.getTitle())
                .category(quiz.getQuizCategory().getTitle())
                .quizLevelList(convert(quiz.getQuizLevel()))
                .build();
    };

    private List<String> convert(final List<QuizLevel> quizLevelList) {
        return quizLevelList.stream()
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    QuizEntity toQuizEntity(final Quiz quiz);

    List<QuizEntity> toQuizEntities(List<Quiz> quizList);
}
