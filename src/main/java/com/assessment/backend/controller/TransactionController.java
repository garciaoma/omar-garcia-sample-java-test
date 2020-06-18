package com.assessment.backend.controller;
import ch.qos.logback.classic.Logger;
import com.assessment.backend.dto.TransactionDTO;
import com.assessment.backend.dto.TransactionReportDTO;
import com.assessment.backend.dto.TransactionSumDTO;
import com.assessment.backend.service.TransactionService;
import org.slf4j.LoggerFactory;
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

import static net.logstash.logback.argument.StructuredArguments.kv;

@RestController("/")
public class TransactionController extends BaseController{
    private static Logger logger = (Logger) LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "transaction", method = RequestMethod.POST)
    @ResponseBody
    public TransactionDTO addTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        logger.info("Add transaction with {}", kv("transactionDTO", transactionDTO));
        return transactionService.create(transactionDTO);
    }

    @RequestMapping(value = "transaction/{userId}/{uuid}", method = RequestMethod.GET)
    public TransactionDTO get(@PathVariable Long userId, @PathVariable UUID uuid) {
        logger.info("Get single transaction by {} and {}", kv("userId", userId), kv("transactionId", uuid));
        return transactionService.get(userId, uuid);
    }

    @RequestMapping(value = "transaction/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List<TransactionDTO> getAll(@PathVariable Long userId) {
        logger.info("Get all transactions by {}", kv("userId", userId));
        return transactionService.getAll(userId);
    }

    @RequestMapping(value = "transaction/sum/{userId}", method = RequestMethod.GET)
    public TransactionSumDTO getSumByUser(@PathVariable Long userId) {
        logger.info("Get sum of transactions by {}", kv("userId", userId));
        return transactionService.getSumByUser(userId);
    }

    @RequestMapping(value = "transaction/reporting/{userId}", method = RequestMethod.GET)
    public List<TransactionReportDTO> getReport(@PathVariable Long userId) {
        logger.info("Get reporting of transactions by {}", kv("userId", userId));
        return transactionService.getReport(userId);
    }

    @RequestMapping(value = "transaction/random", method = RequestMethod.GET)
    public TransactionDTO getRandom() {
        logger.info("Get random single transaction");
        return transactionService.getRandom();
    }
}
