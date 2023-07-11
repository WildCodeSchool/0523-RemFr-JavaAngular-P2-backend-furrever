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
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 255)
    @NotNull(message = "Le prénom ne peut pas être nul.")
    @NotBlank(message = "Le prénom ne peut pas être vide.")
    private String firstName;

    @NotNull(message = "La date de naissance ne peut pas être nulle.")
    private LocalDate birthday;

    private Float weight;

    @Column(length = 255)
    private String description;

    @ManyToOne
    @JoinColumn(name = "species_id", nullable = false)
    private Species species;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public @NotNull LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(@NotNull LocalDate birthday) {
        this.birthday = birthday;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

