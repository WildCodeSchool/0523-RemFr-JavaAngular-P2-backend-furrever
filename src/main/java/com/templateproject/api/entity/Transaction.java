package com.templateproject.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Transaction {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    @NotNull(message = "Le nom ne peut pas être nul.")
    @NotBlank(message = "Le nom ne peut pas être vide.")
    private Date dateStart;
    @NotNull(message = "Le nom ne peut pas être nul.")
    @NotBlank(message = "Le nom ne peut pas être vide.")
    private Date dateEnd;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    @OneToOne(mappedBy = "transaction")
    private Comment comment;

    public Transaction(@NotNull Date dateStart, @NotNull Date dateEnd, Boolean status) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.status = status;
    }

    public Transaction() { }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(@NotNull Date dateStart) {
        this.dateStart = dateStart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public @NotNull Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(@NotNull Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
