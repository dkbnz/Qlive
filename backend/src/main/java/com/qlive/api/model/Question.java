package com.qlive.api.model;

import javax.persistence.Entity;

@Entity
public class Question extends BaseModel {

    private String questionText;

    protected Question() {}

    public Question(String questionText) { this.questionText = questionText; }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
