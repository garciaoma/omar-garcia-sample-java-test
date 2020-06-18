package com.assessment.backend.controller;

import com.assessment.backend.util.exception.ExceptionDTO;
import com.assessment.backend.util.exception.TransactionNotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseController {
    static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionDTO methodArgumentNotValidExceptionHandler(Exception e) {
        logger.error("Method Argument Not Valid Exception", e);
        return new ExceptionDTO("There was an error during the server process");
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionDTO exceptionHandler(Exception e) {
        logger.error("Unhandled Exception encountered, replying with 500 - Internal Server Error", e);
        return new ExceptionDTO("There was an error during the server process");
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, HttpMessageNotReadableException.class, JsonMappingException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionDTO illegalArgumentExceptionHandler(Exception e) {
        logger.error(e.getMessage(), e);
        return new ExceptionDTO("The request contained an invalid format or contained invalid values");
    }

    @ExceptionHandler(value = {TransactionNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionDTO transactionNotFound(Exception e) {
        return new ExceptionDTO("Transaction not found");
    }
}
