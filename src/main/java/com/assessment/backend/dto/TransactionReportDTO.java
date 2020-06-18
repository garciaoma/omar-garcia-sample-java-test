package com.assessment.backend.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransactionReportDTO implements Serializable {
    private Long userId;
    private String weekStart;
    private String weekEnd;
    private Long quantity;
    private Double amount;
    private Double totalAmount;

    public TransactionReportDTO() {
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = new BigDecimal(amount).setScale(2, RoundingMode.FLOOR).doubleValue();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = new BigDecimal(totalAmount).setScale(2, RoundingMode.FLOOR).doubleValue();
    }
}
