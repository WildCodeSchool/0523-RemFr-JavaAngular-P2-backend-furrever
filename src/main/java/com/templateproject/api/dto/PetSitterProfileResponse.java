package com.templateproject.api.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetSitterProfileResponse {
    private PetSitterProfile petSitterProfile;
    private List<CommentTemplate> commentTemplateList;
    private List<ServiceTemplate> serviceTemplateList;

    public PetSitterProfileResponse(PetSitterProfile petSitterProfile, List<CommentTemplate> commentTemplateList, List<ServiceTemplate> serviceTemplateList) {
        this.petSitterProfile = petSitterProfile;
        this.commentTemplateList = commentTemplateList;
        this.serviceTemplateList = serviceTemplateList;
    }

    public PetSitterProfileResponse() {
    }

    public PetSitterProfile getPetSitterProfile() {
        return petSitterProfile;
    }

    public void setPetSitterProfile(PetSitterProfile petSitterProfile) {
        this.petSitterProfile = petSitterProfile;
    }

    public List<CommentTemplate> getCommentTemplateList() {
        return commentTemplateList;
    }

    public void setCommentTemplateList(List<CommentTemplate> commentTemplateList) {
        this.commentTemplateList = commentTemplateList;
    }

    public List<ServiceTemplate> getServiceTemplateList() {
        return serviceTemplateList;
    }

    public void setServiceTemplateList(List<ServiceTemplate> serviceTemplateList) {
        this.serviceTemplateList = serviceTemplateList;
    }
}
