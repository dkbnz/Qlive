package com.qlive.api.controller;

import com.qlive.api.model.Option;
import com.qlive.api.model.Question;
import com.qlive.api.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping(path="", produces = "application/json")
    Iterable<Question> fetchAll()
    {
        return questionRepository.findAll();
    }

    @GetMapping(path="/{id}", produces = "application/json")
    ResponseEntity<Question> fetch(@PathVariable String id)
    {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if(!questionOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Question>(questionOptional.get(), HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json", consumes = "application/json")
    Question newQuestion(@RequestBody Question newQuestion) {
        return questionRepository.save(newQuestion);
    }

    @PostMapping(path="/{id}/vote", produces = "application/json", consumes = "application/json")
    Question vote(@PathVariable String id, @RequestBody Set<Long> optionIds)
    {
        Question questionToVoteOn = questionRepository.findById(id).get();
        for (Option option : questionToVoteOn.getQuestionOptions()) {
            if(optionIds.contains(option.getId())) {
                option.addVote();
            }
        }
        return questionRepository.save(questionToVoteOn);
    }
}
