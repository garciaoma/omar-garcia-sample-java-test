package com.assessment.backend.service;

import com.assessment.backend.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    TransactionDTO create(TransactionDTO transactionDTO);

    List<TransactionDTO> getAll();
}
