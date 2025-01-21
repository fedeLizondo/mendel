package com.fedelizondo.challenge.application.service;

import com.fedelizondo.challenge.application.port.out.FindByIdTransactionUseCaseOut;
import com.fedelizondo.challenge.application.port.out.GetCumulativeSumForTransactionUseCaseOut;
import com.fedelizondo.challenge.dominio.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetCumulativeSumTransactionUseCaseImplTest {

    GetCumulativeSumTransactionUseCaseImpl getCumulativeSumTransactionUseCaseImpl;
    GetCumulativeSumForTransactionUseCaseOut getCumulativeSumForTransactionUseCaseOut;
    FindByIdTransactionUseCaseOut findByIdTransactionUseCaseOut;

    @BeforeEach
    void setUp() {
        getCumulativeSumForTransactionUseCaseOut = mock(GetCumulativeSumForTransactionUseCaseOut.class);
        findByIdTransactionUseCaseOut = mock(FindByIdTransactionUseCaseOut.class);
        getCumulativeSumTransactionUseCaseImpl = new GetCumulativeSumTransactionUseCaseImpl(
                getCumulativeSumForTransactionUseCaseOut,
                findByIdTransactionUseCaseOut
        );
    }


    @Test
    void givenInvalidIdWhenGetCumulativeSumThenReturnO(){
        //Arrange
        Long invalid = 1337L;
        when(findByIdTransactionUseCaseOut.findById(invalid)).thenReturn(Optional.empty());

        //Act
        double subject = getCumulativeSumTransactionUseCaseImpl.getCumulativeSumForTransaction(invalid);

        //Assert
        assertEquals(0, subject);
    }


    @Test
    void givenValidIdWhenGetCumulativeSumThenReturnNumber(){
        //Arrange
        Long invalid = 1337L;
        Transaction transaction = new Transaction(invalid,10,"test",null);
        when(findByIdTransactionUseCaseOut.findById(invalid)).thenReturn(Optional.of(transaction));
        when(getCumulativeSumForTransactionUseCaseOut.getCumulativeSumForTransaction(invalid)).thenReturn(10D);

        //Act
        double subject = getCumulativeSumTransactionUseCaseImpl.getCumulativeSumForTransaction(invalid);

        //Assert
        assertEquals(10D, subject);
    }
}