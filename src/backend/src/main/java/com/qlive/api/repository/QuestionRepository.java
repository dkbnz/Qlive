package com.qlive.api.repository;

import com.qlive.api.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface QuestionRepository extends PagingAndSortingRepository<Question, String> {
    Page<Question> findQuestionsByQuestionTextContainsAndIsPublicTrueOrderByCreatedDesc(String query, Pageable restrictions);
}
