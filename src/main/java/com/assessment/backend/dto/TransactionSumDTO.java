package com.assessment.backend.dto;

public class TransactionSumDTO {
    private Long userId;
    private Double sum;

    public TransactionSumDTO() {
    }

    public TransactionSumDTO(Long userId, Double sum) {
        this.userId = userId;
        this.sum = sum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}