package com.qlive.api.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
public class Question {

    @Id
    @GenericGenerator(name="accessKey", strategy="com.qlive.api.util.KeyGenerator")
    @GeneratedValue(generator="accessKey")
    private String id;

    private Instant created;

    private Instant updated;

    private String questionText;

    private Boolean multiselect = false;

    private Boolean isPublic = false;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Option> questionOptions;

    private Integer voteCount = 0;

    protected Question() {}

    @PrePersist
    protected void onCreate() {
        created = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = Instant.now();
    }

    public void addVote() {
        voteCount += 1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreated() {
        return created.toEpochMilli();
    }

    public Integer getVoteCount() {
        return voteCount;
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

    public Boolean getPublic() { return isPublic; }

    public void setPublic(Boolean aPublic) { isPublic = aPublic; }

    public List<Option> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<Option> questionOptions) {
        this.questionOptions = questionOptions;
    }
}
