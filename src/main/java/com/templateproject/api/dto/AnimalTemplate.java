package com.templateproject.api.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class AnimalTemplate {
    private UUID idAnimal;
    private String firstName;
    private LocalDate birthday;
    private Float weight;
    private String description;
    private String species;

    public AnimalTemplate(UUID idAnimal, String firstName, LocalDate birthday, Float weight, String description, String species) {
        this.idAnimal = idAnimal;
        this.firstName = firstName;
        this.birthday = birthday;
        this.weight = weight;
        this.description = description;
        this.species = species;
    }

    public AnimalTemplate() {
    }

    public UUID getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(UUID idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
