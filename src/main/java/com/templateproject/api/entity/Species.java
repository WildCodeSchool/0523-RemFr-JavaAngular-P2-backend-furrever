package com.templateproject.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Entity
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 255)
    @NotNull(message = "Le nom ne peut pas être nul.")
    @NotBlank(message = "Le nom ne peut pas être vide.")
    private String name;

    @OneToMany(mappedBy = "species")
    private List<Animal> animals;

    @ManyToOne
    //on spécifie dans service quelle est la clef étrangère que l'on veut mettre
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    public Species( @NotNull String name) {
        this.name = name;
    }

    public Species() {

    }

    public UUID getId() {

        return id;
    }

    public void setId(UUID id) {

        this.id = id;
    }

    public @NotNull String getName() {

        return name;
    }

    public void setName(@NotNull String name) {

        this.name = name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
