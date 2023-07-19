package com.templateproject.api.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchRequest {
    private String city;
    private String typeService;
    private List<String> speciesList;

    public SearchRequest(String city, String typeService, List<String> speciesList) {
        this.city = city;
        this.typeService = typeService;
        this.speciesList = speciesList;
    }

    public SearchRequest() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public List<String> getSpeciesList() {
        return speciesList;
    }

    public void setSpecies(List<String> speciesList) {
        this.speciesList = speciesList;
    }
}
