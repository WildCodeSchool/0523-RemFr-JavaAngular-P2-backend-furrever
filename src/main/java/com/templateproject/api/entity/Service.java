package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 255)
    private String description;

    @NotNull(message = "Le prix ne peut pas etre nul.")
    private Float price;

    @Column(length = 255)
    @NotNull(message = "Le service ne peut pas être nul.")
    @NotBlank(message = "Le service ne peut pas être vide.")
    private String typeService;

    private Float weightMin;

    private Float weightMax;

    private Boolean isHealer = false;

    private Boolean active = true;

    @OneToMany(mappedBy = "service")
    private List<Transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "service_species",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "species_id")
    )
    private List<Species> speciesList;

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
