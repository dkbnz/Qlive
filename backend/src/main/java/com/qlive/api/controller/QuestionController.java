package com.qlive.api.controller;

import com.qlive.api.model.Question;
import com.qlive.api.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping(path="/", produces = "application/json")
    public Iterable<Question> fetchQuestions()
    {
        questionRepository.save(new Question("What is the time?"));
        return questionRepository.findAll();
    }
}
