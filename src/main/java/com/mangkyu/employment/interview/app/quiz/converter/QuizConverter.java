package com.mangkyu.employment.interview.app.quiz.converter;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.AddQuizRequest;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.GetQuizResponse;
import com.mangkyu.employment.interview.app.quiz.domain.QuizLevel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface QuizConverter {

    QuizConverter INSTANCE = Mappers.getMapper(QuizConverter.class);

    QuizEntity toQuizEntity(final AddQuizRequest addQuizRequest);

    default GetQuizResponse toGetQuizResponse(final QuizEntity quizEntity) {
        return GetQuizResponse.builder()
                .resourceId(quizEntity.getResourceId())
                .title(quizEntity.getTitle())
                .category(quizEntity.getQuizCategory().getTitle())
                .quizLevelList(convert(quizEntity.getQuizLevel()))
                .createdAt(Timestamp.valueOf(quizEntity.getCreatedAt()).getTime())
                .build();
    };

    private List<String> convert(final List<QuizLevel> quizLevelList) {
        return quizLevelList.stream()
                .map(Enum::name)
                .collect(Collectors.toList());
    }

}
