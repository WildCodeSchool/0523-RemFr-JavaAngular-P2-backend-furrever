package com.templateproject.api.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class TransactionUserTemplate {
    private UUID idTransaction;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Boolean status;
    private String content;
    private String typeService;
    private Float price;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerEmail;
    private String petSitterFirstName;
    private String petSitterLastName;
    private Boolean commentedTransaction = false;


    public TransactionUserTemplate(UUID idTransaction, LocalDate dateStart, LocalDate dateEnd, Boolean status, String content, String typeService, Float price, String ownerFirstName, String ownerLastName, String ownerEmail, String petSitterFirstName, String petSitterLastName) {
        this.idTransaction = idTransaction;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.status = status;
        this.content = content;
        this.typeService = typeService;
        this.price = price;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
        this.ownerEmail = ownerEmail;
        this.petSitterFirstName = petSitterFirstName;
        this.petSitterLastName = petSitterLastName;
    }

    public TransactionUserTemplate() {
    }

    public UUID getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(UUID idTransaction) {
        this.idTransaction = idTransaction;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getPetSitterFirstName() {
        return petSitterFirstName;
    }

    public void setPetSitterFirstName(String petSitterFirstName) {
        this.petSitterFirstName = petSitterFirstName;
    }

    public String getPetSitterLastName() {
        return petSitterLastName;
    }

    public void setPetSitterLastName(String petSitterLastName) {
        this.petSitterLastName = petSitterLastName;
    }

    public Boolean getCommentedTransaction() {
        return commentedTransaction;
    }

    public void setCommentedTransaction(Boolean commentedTransaction) {
        this.commentedTransaction = commentedTransaction;
    }
}
