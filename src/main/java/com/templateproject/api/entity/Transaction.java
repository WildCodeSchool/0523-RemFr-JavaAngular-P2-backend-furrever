package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Transaction {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    @NotNull(message = "Le nom ne peut pas être nul.")
    private LocalDate dateStart;
    @NotNull(message = "Le nom ne peut pas être nul.")
    private LocalDate dateEnd;
    private Boolean status;

    @Lob
    @Column(length = 500)
    private String content;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = true)
    private Service service;
    @OneToOne(mappedBy = "transaction", cascade = CascadeType.REMOVE)
    private Comment comment;

    public UUID getId() {

        return id;
    }

    public void setId(UUID id) {

        this.id = id;
    }

    public @NotNull LocalDate getDateStart() {

        return dateStart;
    }

    public void setDateStart(@NotNull LocalDate dateStart) {

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

    public @NotNull LocalDate getDateEnd() {

        return dateEnd;
    }

    public void setDateEnd(@NotNull LocalDate dateEnd) {

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
