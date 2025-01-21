package com.fedelizondo.challenge.infrastructure.adapter.in.rest.controller;

import com.fedelizondo.challenge.application.port.in.CreateTransactionUseCase;
import com.fedelizondo.challenge.application.port.in.FindTransactionIdsByTypeUseCase;
import com.fedelizondo.challenge.application.port.in.GetCumulativeSumTransactionUseCase;
import com.fedelizondo.challenge.dominio.model.Transaction;
import com.fedelizondo.challenge.infrastructure.adapter.in.rest.request.TransactionRequest;
import com.fedelizondo.challenge.infrastructure.adapter.in.rest.response.TransactionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransactionControllerTest {

    TransactionController transactionController;
    CreateTransactionUseCase createTransactionUseCase;
    FindTransactionIdsByTypeUseCase findTransactionIdsByTypeUseCase;
    GetCumulativeSumTransactionUseCase getCumulativeSumTransactionUseCase;

    @BeforeEach
    void setUp() {
        createTransactionUseCase = mock(CreateTransactionUseCase.class);
        findTransactionIdsByTypeUseCase = mock(FindTransactionIdsByTypeUseCase.class);
        getCumulativeSumTransactionUseCase = mock(GetCumulativeSumTransactionUseCase.class);
        transactionController = new TransactionController(
                createTransactionUseCase,
                findTransactionIdsByTypeUseCase,
                getCumulativeSumTransactionUseCase
        );
    }

    @Test
    void givenNewTransactionWhenSaveTransactionThenReturn200() {
        //Arrange
        long transactionId = 1L;
        double amount = 100.00;
        String type = "income";
        Long parentId = null;

        TransactionRequest transactionRequest = new TransactionRequest(amount, type, parentId);
        Transaction expectedSavedTransaction = new Transaction(transactionId, amount, type, parentId);

        when(createTransactionUseCase.createTransaction(expectedSavedTransaction)).thenReturn(expectedSavedTransaction);

        // Act
        ResponseEntity<TransactionResponse> response = transactionController.saveTransaction(transactionId, transactionRequest);

        // Assert
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        TransactionResponse actualTransactionResponse = response.getBody();

        assertEquals(transactionId, actualTransactionResponse.getTransactionId());
        assertEquals(amount, actualTransactionResponse.getAmount());
        assertEquals(type, actualTransactionResponse.getType());
        assertEquals(parentId, actualTransactionResponse.getParentId());
    }

    @Test
    void givenTypeWhenFindTransactionByTypeThenReturn200() {
        //Arrange
        String find = "TEST";

        when(findTransactionIdsByTypeUseCase.findTransactionByType(find.toLowerCase(Locale.ROOT)))
                .thenReturn(List.of(1L));
        //Act
        ResponseEntity<List<Long>> response = transactionController.findTransactionByType(find);

        //Assert
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        List<Long> ids = response.getBody();
        assertEquals(1, ids.size());
        assertEquals(1L, ids.get(0));
    }

    @Test
    void givenValidIdWhenGetTransactionSumThenReturnSum() {
        //Arrange
        Long search = 1L;
        when(getCumulativeSumTransactionUseCase.getCumulativeSumForTransaction(search)).thenReturn(10D);

        //Act
        ResponseEntity<Double> response = transactionController.getTransactionSum(search);

        //Assert
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Double sum = response.getBody();

        assertEquals(10D, sum);
    }

}