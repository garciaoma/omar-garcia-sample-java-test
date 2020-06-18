package com.assessment.backend.controller;

import com.assessment.backend.dto.TransactionDTO;
import com.assessment.backend.dto.TransactionReportDTO;
import com.assessment.backend.dto.TransactionSumDTO;
import com.assessment.backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController("/")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "transaction", method = RequestMethod.POST)
    @ResponseBody
    public TransactionDTO addTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        return transactionService.create(transactionDTO);
    }

    @RequestMapping(value = "transaction/{userId}/{uuid}", method = RequestMethod.GET)
    public TransactionDTO get(@PathVariable Long userId, @PathVariable UUID uuid) {
        return transactionService.get(userId, uuid);
    }

    @RequestMapping(value = "transaction/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List<TransactionDTO> getAll(@PathVariable Long userId) {
        return transactionService.getAll(userId);
    }

    @RequestMapping(value = "transaction/sum/{userId}", method = RequestMethod.GET)
    public TransactionSumDTO getSumByUser(@PathVariable Long userId) {
        return transactionService.getSumByUser(userId);
    }

    @RequestMapping(value = "transaction/reporting/{userId}", method = RequestMethod.GET)
    public List<TransactionReportDTO> getReport(@PathVariable Long userId) {
        return transactionService.getReport(userId);
    }

    @RequestMapping(value = "transaction/random", method = RequestMethod.GET)
    public TransactionDTO getRandom() {
        return transactionService.getRandom();
    }
}
