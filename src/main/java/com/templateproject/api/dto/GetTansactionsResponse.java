package com.templateproject.api.dto;

import java.util.ArrayList;
import java.util.List;

public class GetTansactionsResponse {
    private List<TransactionUserTemplate> transationForPetsitter = new ArrayList<>();
    private List<TransactionUserTemplate> transationFromUser = new ArrayList<>();
    private Boolean petsitter;

    public GetTansactionsResponse() {
    }

    public List<TransactionUserTemplate> getTransationForPetsitter() {
        return transationForPetsitter;
    }

    public void setTransationForPetsitter(List<TransactionUserTemplate> transationForPetsitter) {
        this.transationForPetsitter = transationForPetsitter;
    }

    public List<TransactionUserTemplate> getTransationFromUser() {
        return transationFromUser;
    }

    public void setTransationFromUser(List<TransactionUserTemplate> transationFromUser) {
        this.transationFromUser = transationFromUser;
    }

    public Boolean getPetsitter() {
        return petsitter;
    }

    public void setPetsitter(Boolean petsitter) {
        this.petsitter = petsitter;
    }
}
