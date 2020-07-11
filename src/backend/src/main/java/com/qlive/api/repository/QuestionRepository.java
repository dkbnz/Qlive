package com.qlive.api.repository;

import com.qlive.api.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, String> {
    List<Question> findQuestionsByIsPublicTrueOrderByCreatedDesc();
    List<Question> findQuestionsByQuestionTextContainsAndIsPublicTrueOrderByCreatedDesc(String questionText);
}
