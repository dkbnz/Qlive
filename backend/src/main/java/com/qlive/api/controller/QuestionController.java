package com.qlive.api.controller;

import com.qlive.api.model.Question;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/question")
public class QuestionController {

    @GetMapping(path="/", produces = "application/json")
    public Question fetchQuestion()
    {
        return new Question("What is the time?");
    }
}
