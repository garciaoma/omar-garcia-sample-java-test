package com.assessment.backend.controller;

import com.assessment.backend.dto.TransactionDTO;
import com.assessment.backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController("/")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "transaction", method = RequestMethod.POST)
    @ResponseBody
    public TransactionDTO addTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        return transactionService.create(transactionDTO);
    }

    @RequestMapping(value = "transaction", method = RequestMethod.GET)
    @ResponseBody
    public List<TransactionDTO> getAll() {
        return transactionService.getAll();
    }
}
