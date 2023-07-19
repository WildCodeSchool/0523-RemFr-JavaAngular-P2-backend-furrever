package com.templateproject.api.dto;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SpeciesResponse {
    private UUID id;
    private String name;

    public SpeciesResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public SpeciesResponse() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
