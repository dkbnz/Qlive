package com.qlive.api.controller;

import com.qlive.api.model.Question;
import com.qlive.api.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping(path="/", produces = "application/json")
    Iterable<Question> fetchAll()
    {
        Question question = new Question("What is the time?");
        questionRepository.save(question);
        return questionRepository.findAll();
    }

    @GetMapping(path="/{id}", produces = "application/json")
    Question fetch(@PathVariable String id)
    {
        return questionRepository.findById(id).get();
    }

    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    Question newQuestion(@RequestBody Question newQuestion) {
        return questionRepository.save(newQuestion);
    }
}
