package com.templateproject.api.dto;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserProfile {
    private UUID idUser;
    private String email;
    private String firstname;
    private String lastname;
    private String picture;

    public UserProfile(UUID idUser, String email, String firstname, String lastname, String picture) {
        this.idUser = idUser;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.picture = picture;
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
}
