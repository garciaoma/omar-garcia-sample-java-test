package com.assessment.backend.dto;

import com.assessment.backend.util.CheckValidTransaction;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@CheckValidTransaction
public class TransactionDTO implements Serializable {
    private UUID transactionId;
    private Double amount;
    private String description;
    private Date date;
    private Long userId;

    public TransactionDTO() {
    }

    public TransactionDTO(UUID transactionId, Double amount, String description, Date date, Long userId) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.userId = userId;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
