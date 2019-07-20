package com.qlive.api.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Question {

    @Id
    @GenericGenerator(name="accessKey", strategy="com.qlive.api.util.KeyGenerator")
    @GeneratedValue(generator="accessKey")
    private String id;

    private String questionText;

    @CreationTimestamp
    private Date createdDate;

    protected Question() {}

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
