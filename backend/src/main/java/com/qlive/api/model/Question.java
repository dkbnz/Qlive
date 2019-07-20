package com.qlive.api.model;

public class Question extends BaseModel {

    private String questionText;

    public Question() {}

    public Question(String questionText) { this.questionText = questionText; }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
