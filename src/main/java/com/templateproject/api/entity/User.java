package com.templateproject.api.entity;

import jakarta.annotation.Resource;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.*;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique=true, length = 255)
    @NotNull(message = "L'email ne peut pas être nul.")
    @NotBlank(message = "L'email ne peut pas être vide.")
    private String email;

    @NotNull(message = "Le mot de passe ne peut pas être nul.")
    @NotBlank(message = "Le mot de passe ne peut pas être vide.")
    @Column(length = 255)
    private String password;

    @Column(length = 255)
    @NotNull(message = "Le prénom ne peut pas être nul.")
    @NotBlank(message = "Le prénom ne peut pas être vide.")
    private String firstName;

    @Column(length = 255)
    @NotNull(message = "Le nom ne peut pas être nul.")
    @NotBlank(message = "Le nom ne peut pas être vide.")
    private String lastName;

    @Column(length = 255)
    private String description;

    @Column(length = 255)
    private String picture;

    private Boolean isPetSitter = false;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Service> services;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Animal> animals;

      public UUID getId() {

        return id;
    }

    public void setId(UUID id) {

        this.id = id;
    }

    public Boolean getPetSitter() {

        return isPetSitter;
    }

    public void setPetSitter(Boolean petSitter) {

        isPetSitter = petSitter;
    }

    public List<Transaction> getTransactions() {

        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {

        this.transactions = transactions;
    }

    public @NotNull String getEmail() {

        return email;
    }

    public void setEmail(@NotNull String email) {

        this.email = email;
    }

    public @NotNull String getPassword() {

        return password;
    }

    public void setPassword(@NotNull String password) {

        this.password = password;
    }

    public @NotNull String getFirstName() {

        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {

        this.firstName = firstName;
    }

    public @NotNull String getLastName() {

        return lastName;
    }

    public void setLastName(@NotNull String lastName) {

        this.lastName = lastName;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getPicture() {

        return picture;
    }

    public void setPicture(String picture) {

        this.picture = picture;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
