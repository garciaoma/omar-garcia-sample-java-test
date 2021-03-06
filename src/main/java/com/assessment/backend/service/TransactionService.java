package com.assessment.backend.service;

import com.assessment.backend.dto.TransactionDTO;
import com.assessment.backend.dto.TransactionReportDTO;
import com.assessment.backend.dto.TransactionSumDTO;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    TransactionDTO create(TransactionDTO transactionDTO);

    List<TransactionDTO> getAll(Long userId);

    TransactionDTO get(Long userId, UUID uuid);

    TransactionSumDTO getSumByUser(Long userId);

    List<TransactionReportDTO> getReport(Long userId);

    TransactionDTO getRandom();
}
