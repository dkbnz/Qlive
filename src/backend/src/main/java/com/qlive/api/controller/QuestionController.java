package com.qlive.api.controller;

import com.qlive.api.model.Option;
import com.qlive.api.model.Question;
import com.qlive.api.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;


    @GetMapping(path="", produces = "application/json")
    ResponseEntity<List<Question>> queryQuestions(
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        Pageable restrictions = PageRequest.of(pageNo, pageSize);
        Page<Question> result = questionRepository.findQuestionsByQuestionTextContainsAndIsPublicTrueOrderByCreatedDesc(query, restrictions);
        return new ResponseEntity<>(result.getContent(), HttpStatus.OK);
    }


    @GetMapping(path="/{id}", produces = "application/json")
    ResponseEntity<Question> fetch(@PathVariable String id)
    {
        Optional<Question> questionOptional = questionRepository.findById(id);
        return questionOptional.map(question -> new ResponseEntity<>(question, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping(value = "", produces = "application/json", consumes = "application/json")
    Question newQuestion(@RequestBody Question newQuestion) {
        return questionRepository.save(newQuestion);
    }


    @PostMapping(path="/{id}/vote", produces = "application/json", consumes = "application/json")
    ResponseEntity<Question> vote(@PathVariable String id, @RequestBody Set<Long> optionIds)
    {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if(!questionOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Question questionToVoteOn = questionOptional.get();
        for (Option option : questionToVoteOn.getQuestionOptions()) {
            if(optionIds.contains(option.getId())) {
                option.addVote();
                questionToVoteOn.addVote();
            }
        }

        return new ResponseEntity<>(questionRepository.save(questionToVoteOn), HttpStatus.OK);
    }
}
