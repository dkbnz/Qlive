package com.qlive.api.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Question {

    @Id
    @GenericGenerator(name="accessKey", strategy="com.qlive.api.util.KeyGenerator")
    @GeneratedValue(generator="accessKey")
    private String id;

    @CreationTimestamp
    private Date createdDate;

    private String questionText;

    private Boolean multiselect = false;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Option> questionOptions;

    protected Question() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Boolean getMultiselect() {
        return multiselect;
    }

    public void setMultiselect(Boolean multiselect) {
        this.multiselect = multiselect;
    }

    public List<Option> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<Option> questionOptions) {
        this.questionOptions = questionOptions;
    }
}
