package com.templateproject.api.dto;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PetSitterProfile {
    private UUID idPetterSitter;
    private String email;
    private String firstname;
    private String lastname;
    private String description;
    private String picture;
    private String street;
    private String city;
    private String zipCode;

    public PetSitterProfile(UUID idPetterSitter, String email, String firstname, String lastname, String description, String picture, String street, String city, String zipCode) {
        this.idPetterSitter = idPetterSitter;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
        this.picture = picture;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public PetSitterProfile() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public UUID getIdPetterSitter() {
        return idPetterSitter;
    }

    public void setIdPetterSitter(UUID idPetterSitter) {
        this.idPetterSitter = idPetterSitter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
}
