package com.fedelizondo.challenge.infrastructure.adapter.out.persistence.memory.repository;

import com.fedelizondo.challenge.application.port.in.CreateTransactionUseCase;
import com.fedelizondo.challenge.dominio.model.Transaction;
import com.fedelizondo.challenge.infrastructure.adapter.in.rest.controller.TransactionController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TransactionRepositoryTest {

    TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository = new TransactionRepository();
    }

    @AfterEach
    void tearDown() {
        TransactionRepository.clearData();
    }

    @Test
    void givenExistingIdWhenFindByIdThenReturnTransaction() {
        //Arrange
        Long findId = 1L;
        double amount = 10;
        String type = "test";
        Long parentId = null;
        Transaction transaction = new Transaction(findId, amount, type, parentId);
        transactionRepository.save(transaction);

        //Act
        Transaction subject = transactionRepository.findById(findId).orElse(null);

        //Assert
        assertEquals(findId, subject.id());
        assertEquals(amount, subject.amount());
        assertEquals(type, subject.type());
        assertEquals(parentId, subject.parentId());
    }

    @Test
    void givenNotExistingIdWhenFindByIdThenReturnTransaction() {
        //Arrange
        Long findId = 1L;
        double amount = 10;
        String type = "test";
        Long parentId = null;

        //Act
        Transaction subject = transactionRepository.findById(findId).orElse(null);

        //Assert
        assertNull(subject);
    }
}