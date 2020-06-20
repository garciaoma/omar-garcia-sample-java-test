package com.assessment.backend.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransactionSumDTO implements Serializable {
    private Long userId;
    private Double sum;

    public TransactionSumDTO() {
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
        this.sum = new BigDecimal(sum).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
