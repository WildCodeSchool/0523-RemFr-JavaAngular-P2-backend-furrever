package com.templateproject.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 255)
    private String description;

    @NotNull(message = "Le prix ne peut pas etre nul.")
    @NotBlank(message = "Le prix ne peut pas être vide.")
    private Float price;

    @Column(length = 255)
    @NotNull(message = "Le service ne peut pas être nul.")
    @NotBlank(message = "Le service ne peut pas être vide.")
    private String typeService;

    @Column(length = 255)
    @NotNull(message = "L'espèce ne peut pas être nulle.")
    @NotBlank(message = "L'espèce ne peut pas être vide.")
    private String species;

    private Float weightMin;

    private Float weightMax;

    private Boolean isHealer = false;

    @OneToMany(mappedBy = "service")
    private List<Transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "service")
    private List<Species> speciesList;

    public Service(
            String description,
            @NotNull Float price,
            @NotNull String typeService,
            @NotNull String species,
            Float weightMin,
            Float weightMax,
            Boolean isHealer
    ) {
        this.description = description;
        this.price = price;
        this.typeService = typeService;
        this.species = species;
        this.weightMin = weightMin;
        this.weightMax = weightMax;
        this.isHealer = isHealer;
    }

    public Service() { }

    public UUID getId() {

        return id;
    }

    public void setId(UUID id) {

        this.id = id;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public @NotNull Float getPrice() {

        return price;
    }

    public void setPrice(@NotNull Float price) {

        this.price = price;
    }

    public @NotNull String getTypeService() {

        return typeService;
    }

    public void setTypeService(@NotNull String typeService) {

        this.typeService = typeService;
    }

    public @NotNull String getSpecies() {

        return species;
    }

    public void setSpecies(@NotNull String species) {

        this.species = species;
    }

    public Float getWeightMin() {

        return weightMin;
    }

    public void setWeightMin(Float weightMin) {

        this.weightMin = weightMin;
    }

    public Float getWeightMax() {

        return weightMax;
    }

    public void setWeightMax(Float weightMax) {

        this.weightMax = weightMax;
    }

    public void setHealer(Boolean healer) {

        isHealer = healer;
    }

    public Boolean getHealer() {

        return isHealer;
    }

    public List<Transaction> getTransactions() {

        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {

        this.transactions = transactions;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    public List<Species> getSpeciesList() {
        return speciesList;
    }

    public void setSpeciesList(List<Species> speciesList) {
        this.speciesList = speciesList;
    }
}