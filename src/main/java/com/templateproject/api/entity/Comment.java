package com.templateproject.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.w3c.dom.Text;

import java.util.Date;
import java.util.UUID;

@Entity
public class Comment {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "La content ne peut pas être vide.")
    @NotNull(message = "La content ne peut pas être nulle.")
    @Column(length = 500)
    private String content;
    @NotBlank(message = "La note ne peut pas être vide.")
    @NotNull(message = "La note ne peut pas être nulle.")
    private Integer note;
    private Date date;
    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public Comment(@NotNull String content, @NotNull Integer note, Date date) {
        this.content = content;
        this.note = note;
        this.date = date;
    }

    public Comment() { }

    public UUID getId() { return id; }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull Integer getNote() {
        return note;
    }

    public void setNote(@NotNull Integer note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public @NotNull String getContent() {
        return content;
    }

    public void setContent(@NotNull String content) {
        this.content = content;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
