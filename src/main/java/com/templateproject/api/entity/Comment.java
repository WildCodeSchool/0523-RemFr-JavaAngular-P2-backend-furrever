package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "La content ne peut pas être vide.")
    @NotNull(message = "La content ne peut pas être nulle.")
    @Lob
    @Column(length = 500)
    private String content;

    @NotNull(message = "La note ne peut pas être nulle.")
    private Integer note;

    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {

        this.id = id;
    }

    public @NotNull Integer getNote() {

        return note;
    }

    public void setNote(@NotNull Integer note) {

        this.note = note;
    }

    public LocalDate getDate() {

        return date;
    }

    public void setDate(LocalDate date) {

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
