package com.assessment.backend.controller;

import com.assessment.backend.dto.TransactionDTO;
import com.assessment.backend.service.TransactionService;
import com.assessment.backend.util.exception.TransactionNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TransactionControllerUnitTests {
    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    private static final List<TransactionDTO> TRANSACTIONS_LIST = new ArrayList<>();
    static {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
        transactionDTO.setUserId(1L);
        transactionDTO.setDescription("Automated description");
        transactionDTO.setDate(new Date());
        transactionDTO.setAmount(10.23);
        TRANSACTIONS_LIST.add(transactionDTO);
        transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(UUID.fromString("123e4567-e89b-12d3-a456-556642440001"));
        transactionDTO.setUserId(1L);
        transactionDTO.setDescription("Automated description");
        transactionDTO.setDate(new Date());
        transactionDTO.setAmount(45.46);
        TRANSACTIONS_LIST.add(transactionDTO);
        transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(UUID.fromString("123e4567-e89b-12d3-a456-556642440002"));
        transactionDTO.setUserId(1L);
        transactionDTO.setDescription("Automated description");
        transactionDTO.setDate(new Date());
        transactionDTO.setAmount(75.68);
        TRANSACTIONS_LIST.add(transactionDTO);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void addTransactionSuccess() throws Exception {
        when(transactionService.create(ArgumentMatchers.any(TransactionDTO.class)))
            .thenReturn(TRANSACTIONS_LIST.get(0));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(TRANSACTIONS_LIST.get(0));

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().is(200));

        verify(transactionService).create(ArgumentMatchers.any(TransactionDTO.class));
    }

    @Test
    public void addTransactionValidationFailure() throws Exception {
        when(transactionService.create(ArgumentMatchers.any(TransactionDTO.class)))
            .thenReturn(TRANSACTIONS_LIST.get(0));

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction").contentType(APPLICATION_JSON_UTF8).content(""))
                .andExpect(status().is(400));

        verify(transactionService, times(0)).create(ArgumentMatchers.any(TransactionDTO.class));
    }

    @Test
    public void addTransactionServiceFailure() throws Exception {
        when(transactionService.create(ArgumentMatchers.any(TransactionDTO.class)))
                .thenThrow(IllegalStateException.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(TRANSACTIONS_LIST.get(0));

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().is(500));

        verify(transactionService).create(ArgumentMatchers.any(TransactionDTO.class));
    }

    @Test()
    public void getAll() throws Exception {
        when(transactionService.getAll(ArgumentMatchers.anyLong()))
                .thenReturn(TRANSACTIONS_LIST);

        mockMvc.perform(MockMvcRequestBuilders.get("/transaction/1").accept(APPLICATION_JSON))
                .andExpect(status().is(200));

        verify(transactionService).getAll(ArgumentMatchers.anyLong());
    }

    @Test()
    public void getAllInvalidUser() throws Exception {
        when(transactionService.getAll(ArgumentMatchers.anyLong()))
                .thenReturn(Collections.emptyList());

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/transaction/12").accept(APPLICATION_JSON))
                .andExpect(status().is(200));

        MvcResult mvcResult = resultActions.andReturn();

        verify(transactionService).getAll(ArgumentMatchers.anyLong());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("[]"));
    }

    @Test()
    public void get() throws Exception {
        when(transactionService.get(ArgumentMatchers.anyLong(), ArgumentMatchers.any(UUID.class)))
                .thenReturn(TRANSACTIONS_LIST.get(0));

        mockMvc.perform(MockMvcRequestBuilders.get("/transaction/1/123e4567-e89b-12d3-a456-556642440000").accept(APPLICATION_JSON))
                .andExpect(status().is(200));

        verify(transactionService).get(ArgumentMatchers.anyLong(), ArgumentMatchers.any(UUID.class));
    }

    @Test()
    public void getFailure() throws Exception {
        when(transactionService.get(ArgumentMatchers.anyLong(), ArgumentMatchers.any(UUID.class)))
                .thenThrow(TransactionNotFoundException.class);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/transaction/1/123e4567-e89b-12d3-a456-556642440000").accept(APPLICATION_JSON))
                .andExpect(status().is(404));

        MvcResult mvcResult = resultActions.andReturn();

        verify(transactionService).get(ArgumentMatchers.anyLong(), ArgumentMatchers.any(UUID.class));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Transaction not found"));
    }
}
