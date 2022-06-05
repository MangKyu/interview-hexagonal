package com.mangkyu.employment.interview.app.quiz.adapter.persistence;

import com.mangkyu.employment.interview.app.quiz.domain.QuizCategory;
import com.mangkyu.employment.interview.app.quiz.domain.QuizLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuizPersistenceRepository extends JpaRepository<QuizEntity, Long> {
    Optional<QuizEntity> findByResourceId(String resourceId);

    default List<QuizEntity> customFindByIdNotInAndQuizCategoryInAndQuizLevel(Set<Long> quizIdSet, Set<QuizCategory> quizCategorySet, QuizLevel quizLevel) {
        return quizIdSet.isEmpty()
                ? findByQuizCategoryInAndQuizLevel(quizCategorySet, quizLevel)
                : findByIdNotInAndQuizCategoryInAndQuizLevel(quizIdSet, quizCategorySet, quizLevel);
    }

    List<QuizEntity> findByQuizCategoryInAndQuizLevel(final Set<QuizCategory> quizCategorySet, final QuizLevel quizLevel);

    List<QuizEntity> findByIdNotInAndQuizCategoryInAndQuizLevel(final Set<Long> quizIdSet, final Set<QuizCategory> quizCategorySet, final QuizLevel quizLevel);

}
