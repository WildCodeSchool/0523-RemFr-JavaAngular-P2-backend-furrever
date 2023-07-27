package com.templateproject.api.dto;

import com.templateproject.api.entity.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserProfileResponse {
    private UserProfile userProfile;
    private List<AnimalTemplate> animalTemplateList = new ArrayList<>();
    private Integer nbTransactionStatusNull;
    private List<ServiceTemplate> serviceTemplateList = new ArrayList<>();
    private List<CommentTemplate> commentTemplateList = new ArrayList<>();

    public UserProfileResponse(UserProfile userProfile, List<AnimalTemplate> animalTemplateList, Integer nbTransactionStatusNull, List<ServiceTemplate> serviceTemplateList, List<CommentTemplate> commentTemplateList) {
        this.userProfile = userProfile;
        this.animalTemplateList = animalTemplateList;
        this.nbTransactionStatusNull = nbTransactionStatusNull;
        this.serviceTemplateList = serviceTemplateList;
        this.commentTemplateList = commentTemplateList;
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

    public Integer getNbTransactionStatusNull() {
        return nbTransactionStatusNull;
    }

    public void setNbTransactionStatusNull(Integer nbTransactionStatusNull) {
        this.nbTransactionStatusNull = nbTransactionStatusNull;
    }

    public List<ServiceTemplate> getServiceTemplateList() {
        return serviceTemplateList;
    }

    public void setServiceTemplateList(List<ServiceTemplate> serviceTemplateList) {
        this.serviceTemplateList = serviceTemplateList;
    }

    public List<CommentTemplate> getCommentTemplateList() {
        return commentTemplateList;
    }

    public void setCommentTemplateList(List<CommentTemplate> commentTemplateList) {
        this.commentTemplateList = commentTemplateList;
    }
}
