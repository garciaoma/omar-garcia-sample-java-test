package com.assessment.backend.service;

import com.assessment.backend.dto.TransactionDTO;

public interface TransactionService {
    TransactionDTO create(TransactionDTO transactionDTO);
}
