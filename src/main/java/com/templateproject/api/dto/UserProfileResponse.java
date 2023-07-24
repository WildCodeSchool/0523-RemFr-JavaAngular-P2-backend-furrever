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
    private List<ServiceTemplate> serviceTemplateList = new ArrayList<>();

    public UserProfileResponse(UserProfile userProfile, List<AnimalTemplate> animalTemplateList, List<TransactionUserTemplate> transactionUserTemplateList, List<ServiceTemplate> serviceTemplateList) {
        this.userProfile = userProfile;
        this.animalTemplateList = animalTemplateList;
        this.transactionUserTemplateList = transactionUserTemplateList;
        this.serviceTemplateList = serviceTemplateList;
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

    public List<ServiceTemplate> getServiceTemplateList() {
        return serviceTemplateList;
    }

    public void setServiceTemplateList(List<ServiceTemplate> serviceTemplateList) {
        this.serviceTemplateList = serviceTemplateList;
    }
}
