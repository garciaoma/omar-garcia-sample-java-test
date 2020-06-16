package com.assessment.backend.service;

import com.assessment.backend.dto.TransactionDTO;
import com.assessment.backend.dto.TransactionSumDTO;
import com.assessment.backend.dto.model.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TransactionService {
    TransactionDTO create(TransactionDTO transactionDTO);

    List<TransactionDTO> getAll(Long userId);

    TransactionDTO get(Long userId, UUID uuid);

    TransactionSumDTO getSumByUser(Long userId);
}
