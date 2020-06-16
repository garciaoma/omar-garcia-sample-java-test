package com.assessment.backend.service.impl;

import com.assessment.backend.dao.TransactionRepository;
import com.assessment.backend.dto.TransactionDTO;
import com.assessment.backend.dto.model.Transaction;
import com.assessment.backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionDTO create(TransactionDTO transactionDTO) {
        return toTransactionDTO(this.transactionRepository.save(fromDTO(transactionDTO)));
    }

    @Override
    public List<TransactionDTO> getAll() {
        return toTransactionDTOList(transactionRepository.findAll());
    }

    private List<TransactionDTO> toTransactionDTOList(List<Transaction> transactions) {
        return transactions.stream().map(this::toTransactionDTO).collect(Collectors.toList());
    }

    private TransactionDTO toTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(transaction.getId());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setDescription(transaction.getDescription());
        transactionDTO.setDate(transaction.getDate());
        transactionDTO.setUserId(transaction.getUserId());
        return transactionDTO;
    }

    private Transaction fromDTO(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setDate(transactionDTO.getDate());
        transaction.setUserId(transactionDTO.getUserId());
        return transaction;
    }
}
