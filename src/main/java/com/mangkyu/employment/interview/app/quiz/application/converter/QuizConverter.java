package com.mangkyu.employment.interview.app.quiz.application.converter;

import com.mangkyu.employment.interview.app.quiz.adapter.persistence.QuizEntity;
import com.mangkyu.employment.interview.app.quiz.adapter.presentation.AddQuizRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuizConverter {

    QuizConverter INSTANCE = Mappers.getMapper(QuizConverter.class);

    QuizEntity toQuizEntity(final AddQuizRequest addQuizRequest);

}
