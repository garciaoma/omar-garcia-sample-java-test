package com.assessment.backend.util.validator;

import com.assessment.backend.dto.TransactionDTO;
import com.assessment.backend.util.CheckValidTransaction;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TransactionValidator implements ConstraintValidator<CheckValidTransaction, TransactionDTO> {
    private boolean notEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    @Override
    public void initialize(CheckValidTransaction constraintAnnotation) {
    }

    @Override
    public boolean isValid(TransactionDTO transactionDTO, ConstraintValidatorContext constraintValidatorContext) {
        return notEmpty(transactionDTO.getDescription()) && transactionDTO.getAmount() != null && transactionDTO.getDate() != null
                && transactionDTO.getUserId() != null;
    }
}
