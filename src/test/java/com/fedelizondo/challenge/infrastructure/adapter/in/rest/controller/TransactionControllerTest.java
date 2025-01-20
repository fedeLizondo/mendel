package com.fedelizondo.challenge.infrastructure.adapter.in.rest.controller;

import com.fedelizondo.challenge.application.port.in.CreateTransactionUseCase;
import com.fedelizondo.challenge.dominio.model.Transaction;
import com.fedelizondo.challenge.infrastructure.adapter.in.rest.request.TransactionRequest;
import com.fedelizondo.challenge.infrastructure.adapter.in.rest.response.TransactionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransactionControllerTest {

    TransactionController transactionController;
    CreateTransactionUseCase createTransactionUseCase;

    @BeforeEach
    void setUp() {
        createTransactionUseCase = mock(CreateTransactionUseCase.class);
        transactionController = new TransactionController(createTransactionUseCase);
    }


    @Test
    void givenNewTransactionWhenSaveTransactionThenReturn200(){

        long transactionId = 1L;
        double amount = 100.00;
        String type = "income";
        Long parentId = null;

        TransactionRequest transactionRequest = new TransactionRequest(amount, type, parentId);
        Transaction expectedSavedTransaction = new Transaction(transactionId, amount, type, parentId);

        when(createTransactionUseCase.createTransaction(expectedSavedTransaction)).thenReturn(expectedSavedTransaction);

        // When (Act)
        ResponseEntity<TransactionResponse> response = transactionController.saveTransaction(transactionId, transactionRequest);

        // Then (Assert)
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        TransactionResponse actualTransactionResponse = response.getBody();

        assertEquals(transactionId, actualTransactionResponse.getTransactionId());
        assertEquals(amount, actualTransactionResponse.getAmount());
        assertEquals(type, actualTransactionResponse.getType());
        assertEquals(parentId, actualTransactionResponse.getParentId());

    }
}