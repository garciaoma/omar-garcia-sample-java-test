package com.assessment.backend.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionReportDTO implements Serializable {
    private Long userId;
    private String weekStart;
    private String weekEnd;
    private Long quantity;
    private BigDecimal amount;
    private BigDecimal totalAmount;

    public TransactionReportDTO() {
    }

    public TransactionReportDTO(Long userId, String weekStart, String weekEnd, Long quantity, BigDecimal amount, BigDecimal totalAmount) {
        this.userId = userId;
        this.weekStart = weekStart;
        this.weekEnd = weekEnd;
        this.quantity = quantity;
        this.amount = amount;
        this.totalAmount = totalAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(String weekStart) {
        this.weekStart = weekStart;
    }

    public String getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(String weekEnd) {
        this.weekEnd = weekEnd;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
