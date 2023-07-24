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

    public TransactionUserTemplate(UUID idTransaction, LocalDate dateStart, LocalDate dateEnd, Boolean status, String content) {
        this.idTransaction = idTransaction;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.status = status;
        this.content = content;
    }

    public TransactionUserTemplate() {}

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
}
