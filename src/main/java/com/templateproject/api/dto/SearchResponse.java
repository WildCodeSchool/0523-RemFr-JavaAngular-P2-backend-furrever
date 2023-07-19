package com.templateproject.api.dto;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SearchResponse {
    private UUID idPetsitter;
    private String email;
    private String firstname;
    private String lastname;
    private String description;
    private String picture;
    private Float price;
    private Integer rating;
    private Integer ratingQuantity;

    public SearchResponse(UUID idPetsitter, String email, String firstname, String lastname, String description, String picture, Float price, Integer rating, Integer ratingQuantity) {
        this.idPetsitter = idPetsitter;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.rating = rating;
        this.ratingQuantity = ratingQuantity;
    }


    public SearchResponse() {
    }

    public UUID getIdPetsitter() {
        return idPetsitter;
    }

    public void setIdPetsitter(UUID idPetsitter) {
        this.idPetsitter = idPetsitter;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }


    public Integer getRatingQuantity() {
        return ratingQuantity;
    }

    public void setRatingQuantity(Integer ratingQuantity) {
        this.ratingQuantity = ratingQuantity;
    }
}
