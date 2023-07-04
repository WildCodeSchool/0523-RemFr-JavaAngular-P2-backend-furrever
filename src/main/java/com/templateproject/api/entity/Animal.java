package com.templateproject.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 255)
    @NotNull(message = "Le prénom ne peut pas être nul.")
    @NotBlank(message = "Le prénom ne peut pas être vide.")
    private String firstName;
    @Column(length = 255)
    @NotNull(message = "L'espèce ne peut pas être nulle.")
    @NotBlank(message = "L'espèce ne peut pas être vide.")
    private String species;
    @NotNull(message = "La date de naissance ne peut pas être nulle.")
    @NotBlank(message = "La date de naissance ne peut pas être vide.")
    private Date birthday;
    private Float weight;
    @Column(length = 255)
    private String description;

    public Animal(
            @NotNull String firstName,
            @NotNull String species,
            @NotNull Date birthday
    ) {
        this.firstName = firstName;
        this.species = species;
        this.birthday = birthday;
    }

    public Animal() {
    }

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

    public @NotNull String getSpecies() {
        return species;
    }

    public void setSpecies(@NotNull String species) {
        this.species = species;
    }

    public @NotNull Date getBirthday() {
        return birthday;
    }

    public void setBirthday(@NotNull Date birthday) {
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
}
