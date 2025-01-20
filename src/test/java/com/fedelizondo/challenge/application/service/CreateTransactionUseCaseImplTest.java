package com.fedelizondo.challenge.application.service;

import com.fedelizondo.challenge.application.port.out.FindByIdTransactionUseCaseOut;
import com.fedelizondo.challenge.application.port.out.SaveTransactionUseCaseOut;
import com.fedelizondo.challenge.dominio.exception.TransactionAlreadyExistException;
import com.fedelizondo.challenge.dominio.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateTransactionUseCaseImplTest {

    CreateTransactionUseCaseImpl createTransactionUseCase;
    SaveTransactionUseCaseOut saveTransactionUseCaseOut;
    FindByIdTransactionUseCaseOut findByIdTransactionUseCaseOut;

    @BeforeEach
    void setUp() {
        saveTransactionUseCaseOut = mock(SaveTransactionUseCaseOut.class);
        findByIdTransactionUseCaseOut = mock(FindByIdTransactionUseCaseOut.class);
        createTransactionUseCase = new CreateTransactionUseCaseImpl(findByIdTransactionUseCaseOut, saveTransactionUseCaseOut);
    }

    @Test
    void givenExistingIdWhenCreateTransactionThenThrowTransactionAlreadyExistException() {
        //Arrange
        Transaction transaction = new Transaction(1L, 0, "test", null);
        Transaction existingTransaction = new Transaction(1L, 0, "existing", null);

        when(findByIdTransactionUseCaseOut.findById(transaction.id())).thenReturn(Optional.of(existingTransaction));

        //Act
        Exception exception = assertThrows(TransactionAlreadyExistException.class, () -> {
            createTransactionUseCase.createTransaction(transaction);
        });

        //Assert
        assertEquals("Transaction already exist", exception.getMessage());
    }

    @Test
    void givenNewTransactionWhenCreateTransactionThenReturnNewTransaction() {
        //Arrange
        Transaction transaction = new Transaction(1L, 0, "test", null);
        Transaction newTransaction = new Transaction(1L, 0, "existing", null);

        when(findByIdTransactionUseCaseOut.findById(transaction.id())).thenReturn(Optional.empty());
        when(saveTransactionUseCaseOut.save(transaction)).thenReturn(newTransaction);

        //Act
        Transaction subject = createTransactionUseCase.createTransaction(transaction);

        //Assert
        assertEquals(newTransaction.id(), subject.id());
        assertEquals(newTransaction.amount(), subject.amount());
        assertEquals(newTransaction.type(), subject.type());
        assertEquals(newTransaction.parentId(), subject.parentId());
    }
}