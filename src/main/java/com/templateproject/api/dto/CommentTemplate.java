package com.templateproject.api.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CommentTemplate {
    private String content;
    private LocalDate date;
    private Integer note;
    private String firstname;
    private String initialLastName;

    public CommentTemplate(String content, LocalDate date, Integer note, String firstname, String initialLastName) {
        this.content = content;
        this.date = date;
        this.note = note;
        this.firstname = firstname;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getInitialLastName() {
        return initialLastName;
    }

    public void setInitialLastName(String initialLastName) {
        this.initialLastName = initialLastName;
    }
}
