package com.templateproject.api.entity;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CommentTemplate {
    private String content;
    private LocalDate date;
    private Integer note;
    private String firstName;
    private String initialLastName;

    public CommentTemplate(String content, LocalDate date, Integer note, String firstName, String initialLastName) {
        this.content = content;
        this.date = date;
        this.note = note;
        this.firstName = firstName;
        this.initialLastName = initialLastName;
    }

    public CommentTemplate() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getInitialLastName() {
        return initialLastName;
    }

    public void setInitialLastName(String initialLastName) {
        this.initialLastName = initialLastName;
    }
}
