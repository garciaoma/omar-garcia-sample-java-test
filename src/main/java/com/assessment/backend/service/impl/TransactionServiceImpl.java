package com.assessment.backend.service.impl;

import com.assessment.backend.dao.TransactionRepository;
import com.assessment.backend.dao.UserRepository;
import com.assessment.backend.dto.TransactionDTO;
import com.assessment.backend.dto.TransactionSumDTO;
import com.assessment.backend.dto.model.Transaction;
import com.assessment.backend.dto.model.User;
import com.assessment.backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TransactionDTO create(TransactionDTO transactionDTO) {
        return toTransactionDTO(this.transactionRepository.save(fromDTO(transactionDTO)));
    }

    @Override
    public TransactionDTO get(Long userId, UUID uuid) {
        return toTransactionDTO(transactionRepository.findByUserIdAndId(userId, uuid));
    }

    @Override
    public List<TransactionDTO> getAll(Long userId) {
        return toTransactionDTOList(transactionRepository.findByUserIdOrderByDateDesc(userId));
    }

    @Override
    public TransactionSumDTO getSumByUser(Long userId) {
        Map<User, Double> sumResult = transactionRepository.findByUserIdOrderByDateDesc(userId).stream()
                .collect(Collectors.toMap(Transaction::getUser, Transaction::getAmount, Double::sum));

        if (sumResult.isEmpty()) {
            // Or throw an exception
            return new TransactionSumDTO();
        }
        Map.Entry<User, Double> firstResult = sumResult.entrySet().iterator().next();
        return new TransactionSumDTO(firstResult.getKey().getId(), firstResult.getValue());
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
            transactionDTO.setUserId(transaction.getUser().getId());
        return transactionDTO;
    }

    private Transaction fromDTO(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setDate(transactionDTO.getDate());
        transaction.setUser(getUser(transactionDTO.getUserId()).get());
        return transaction;
    }

    private Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }
}
