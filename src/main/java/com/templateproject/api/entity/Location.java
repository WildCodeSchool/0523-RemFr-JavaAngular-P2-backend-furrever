package com.templateproject.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 255)
    @NotNull(message = "La rue ne doit pas être nulle.")
    @NotBlank(message = "La rue ne doit pas être vide.")
    private String street;
    @Column(length = 100)
    @NotNull(message = "Le numéro de rue ne doit pas être nulle.")
    @NotBlank(message = "Le numéro de rue ne doit pas être vide.")
    private String streetNumber;
    @Column(length = 255)
    @NotNull(message = "La ville ne doit pas être nulle.")
    @NotBlank(message = "La ville ne doit pas être vide.")
    private String city;
    @Column(length = 255)
    @NotNull(message = "Le code postal ne doit pas être nul.")
    @NotBlank(message = "Le code postal ne doit pas être vide.")
    private String zipCode;

    public Location(
            @NotNull String street,
            @NotNull String streetNumber,
            @NotNull String city,
            @NotNull String zipCode
    ) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Location() { }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public @NotNull String getStreet() {
        return street;
    }

    public void setStreet(@NotNull String street) {
        this.street = street;
    }

    public @NotNull String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(@NotNull String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public @NotNull String getCity() {
        return city;
    }

    public void setCity(@NotNull String city) {
        this.city = city;
    }

    public @NotNull String getZipCode() {
        return zipCode;
    }

    public void setZipCode(@NotNull String zipCode) {
        this.zipCode = zipCode;
    }
}
