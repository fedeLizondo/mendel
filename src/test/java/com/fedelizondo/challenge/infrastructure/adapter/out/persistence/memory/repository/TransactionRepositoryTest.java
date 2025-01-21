package com.fedelizondo.challenge.infrastructure.adapter.out.persistence.memory.repository;

import com.fedelizondo.challenge.dominio.model.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    void givenNotExistingIdWhenFindByIdThenReturnNull() {
        //Arrange
        Long findId = 1L;

        //Act
        Transaction subject = transactionRepository.findById(findId).orElse(null);

        //Assert
        assertNull(subject);
    }

    @Test
    void givenTransactionWhenSaveReturnTransaction() {
        //Arrange
        Long findId = 1L;
        double amount = 10;
        String type = "test";
        Long parentId = null;
        Transaction transaction = new Transaction(findId, amount, type, parentId);

        //Act
        transactionRepository.save(transaction);
        Transaction subject = transactionRepository.findById(findId).orElse(null);

        //Assert
        assertEquals(findId, subject.id());
        assertEquals(amount, subject.amount());
        assertEquals(type, subject.type());
        assertEquals(parentId, subject.parentId());
    }

    @Test
    void givenExistingTypeWhenFindByTypeThenReturnListOfTransactions() {
        //Arrange
        Long findId = 1L;
        double amount = 10;
        String type = "test";
        Long parentId = null;
        Transaction transaction = new Transaction(findId, amount, type, parentId);
        transactionRepository.save(transaction);

        //Act
        List<Transaction> subject = transactionRepository.findTransactionByType(type);

        //Assert
        assertEquals(1, subject.size());
        Transaction transactionSubject = subject.get(0);
        assertEquals(findId, transactionSubject.id());
        assertEquals(amount, transactionSubject.amount());
        assertEquals(type, transactionSubject.type());
        assertEquals(parentId, transactionSubject.parentId());
    }

    @Test
    void givenNotExistingTypeWhenFindByTypeThenReturnTransaction() {
        //Arrange
        String type = "test";

        //Act
        List<Transaction> subject = transactionRepository.findTransactionByType(type);

        //Assert
        assertTrue(subject.isEmpty());
    }

}