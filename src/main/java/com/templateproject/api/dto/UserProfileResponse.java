package com.templateproject.api.dto;

import com.templateproject.api.entity.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserProfileResponse {
    private UserProfile userProfile;
    private List<AnimalTemplate> animalTemplateList = new ArrayList<>();
    private List<TransactionUserTemplate> transactionUserTemplateList = new ArrayList<>();
    private Location location;

    public UserProfileResponse(UserProfile userProfile, List<AnimalTemplate> animalTemplateList, List<TransactionUserTemplate> transactionUserTemplateList, Location location) {
        this.userProfile = userProfile;
        this.animalTemplateList = animalTemplateList;
        this.transactionUserTemplateList = transactionUserTemplateList;
        this.location = location;
    }

    public UserProfileResponse() {
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public List<AnimalTemplate> getAnimalTemplateList() {
        return animalTemplateList;
    }

    public void setAnimalTemplateList(List<AnimalTemplate> animalTemplateList) {
        this.animalTemplateList = animalTemplateList;
    }

    public List<TransactionUserTemplate> getTransactionUserTemplateList() {
        return transactionUserTemplateList;
    }

    public void setTransactionUserTemplateList(List<TransactionUserTemplate> transactionUserTemplateList) {
        this.transactionUserTemplateList = transactionUserTemplateList;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
