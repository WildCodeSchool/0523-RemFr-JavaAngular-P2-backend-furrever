package com.templateproject.api.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ServiceTemplate {
    private UUID id;
    private String description;
    private Float price;
    private String typeService;
    private Float weightMin;
    private Float weightMax;
    private Boolean isHealer;
    private List<SpeciesResponse> speciesList = new ArrayList<>();

    public ServiceTemplate(UUID id, String description, Float price, String typeService, Float weightMin, Float weightMax, Boolean isHealer) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.typeService = typeService;
        this.weightMin = weightMin;
        this.weightMax = weightMax;
        this.isHealer = isHealer;
    }

    public ServiceTemplate() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<SpeciesResponse> getSpeciesList() {
        return speciesList;
    }

    public void setSpeciesList(List<SpeciesResponse> speciesList) {
        this.speciesList = speciesList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }


    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
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

    public Boolean getHealer() {
        return isHealer;
    }

    public void setHealer(Boolean healer) {
        isHealer = healer;
    }
}
