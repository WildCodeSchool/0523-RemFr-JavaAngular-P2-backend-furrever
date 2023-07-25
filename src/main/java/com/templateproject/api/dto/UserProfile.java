package com.templateproject.api.dto;

import com.templateproject.api.entity.Location;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserProfile {
    private UUID idUser;
    private String email;
    private String firstname;
    private String lastname;
    private String description;
    private String picture;
    private Boolean isPetSitter;
    private Location location;

    public UserProfile(UUID idUser, String email, String firstname, String lastname, String description, String picture, Boolean isPetSitter, Location location) {
        this.idUser = idUser;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
        this.picture = picture;
        this.isPetSitter = isPetSitter;
        this.location = location;
    }

    public UserProfile(){}

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPetSitter() {
        return isPetSitter;
    }

    public void setPetSitter(Boolean petSitter) {
        isPetSitter = petSitter;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
