package com.assessment.backend.dao;

import com.assessment.backend.dto.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
