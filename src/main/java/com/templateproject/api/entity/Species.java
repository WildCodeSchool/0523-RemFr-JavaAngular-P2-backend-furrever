package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 255)
    @NotNull(message = "Le nom ne peut pas être nul.")
    @NotBlank(message = "Le nom ne peut pas être vide.")
    private String name;

    @OneToMany(mappedBy = "species", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Animal> animals;

    @ManyToMany(mappedBy = "speciesList")
    private List<Service> serviceList;

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

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }
}
