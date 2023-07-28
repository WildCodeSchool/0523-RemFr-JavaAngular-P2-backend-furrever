package com.templateproject.api.dto;

import java.util.ArrayList;
import java.util.List;

public class GetTansactionsResponse {
    private List<TransactionUserTemplate> transactionForPetsitter = new ArrayList<>();
    private List<TransactionUserTemplate> transactionFromUser = new ArrayList<>();
    private Boolean petsitter;

    public GetTansactionsResponse() {
    }

    public List<TransactionUserTemplate> getTransactionForPetsitter() {
        return transactionForPetsitter;
    }

    public void setTransactionForPetsitter(List<TransactionUserTemplate> transactionForPetsitter) {
        this.transactionForPetsitter = transactionForPetsitter;
    }

    public List<TransactionUserTemplate> getTransactionFromUser() {
        return transactionFromUser;
    }

    public void setTransactionFromUser(List<TransactionUserTemplate> transactionFromUser) {
        this.transactionFromUser = transactionFromUser;
    }

    public Boolean getPetsitter() {
        return petsitter;
    }

    public void setPetsitter(Boolean petsitter) {
        this.petsitter = petsitter;
    }

}
