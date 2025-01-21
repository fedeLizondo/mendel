package com.fedelizondo.challenge.application.service;

import com.fedelizondo.challenge.application.port.out.FindByTypeTransactionsUseCaseOut;
import com.fedelizondo.challenge.dominio.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindTransactionIdsByTypeUseCaseImplTest {

    FindByTypeTransactionsUseCaseOut findByTypeTransactionsUseCaseOut;
    FindTransactionIdsByTypeUseCaseImpl findTransactionIdsByTypeUseCase;

    @BeforeEach
    void setUp() {
        findByTypeTransactionsUseCaseOut = mock(FindByTypeTransactionsUseCaseOut.class);
        findTransactionIdsByTypeUseCase = new FindTransactionIdsByTypeUseCaseImpl(findByTypeTransactionsUseCaseOut);
    }


    @Test
    void givenValidTypeWhenFindTransactionByTypeThenReturnListOfId() {
        //Arrange
        String find = "test";
        Long transactionId = 1L;
        when(findByTypeTransactionsUseCaseOut.findTransactionByType(find)).thenReturn(List.of(
                new Transaction(transactionId, 10, "test", null)
        ));

        //Act
        List<Long> subject = findTransactionIdsByTypeUseCase.findTransactionByType(find);

        //Assert
        assertEquals(1, subject.size());
        assertTrue(subject.contains(transactionId));
    }

    @Test
    void givenInValidTypeWhenFindTransactionByTypeThenReturnEmptyList() {
        //Arrange
        String find = "test";
        when(findByTypeTransactionsUseCaseOut.findTransactionByType(find)).thenReturn(List.of());

        //Act
        List<Long> subject = findTransactionIdsByTypeUseCase.findTransactionByType(find);

        //Assert
        assertEquals(0, subject.size());
    }

}