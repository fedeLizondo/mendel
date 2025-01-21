package com.fedelizondo.challenge.application.service;

import com.fedelizondo.challenge.application.port.in.CreateTransactionUseCase;
import com.fedelizondo.challenge.application.port.out.FindByIdTransactionUseCaseOut;
import com.fedelizondo.challenge.application.port.out.SaveTransactionUseCaseOut;
import com.fedelizondo.challenge.dominio.exception.TransactionAlreadyExistException;
import com.fedelizondo.challenge.dominio.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final FindByIdTransactionUseCaseOut findByIdTransactionUseCaseOut;
    private final SaveTransactionUseCaseOut saveTransactionUseCaseOut;

    public CreateTransactionUseCaseImpl(FindByIdTransactionUseCaseOut findByIdTransactionUseCaseOut, SaveTransactionUseCaseOut saveTransactionUseCaseOut) {
        this.findByIdTransactionUseCaseOut = findByIdTransactionUseCaseOut;
        this.saveTransactionUseCaseOut = saveTransactionUseCaseOut;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        Transaction searchTransaction = findByIdTransactionUseCaseOut.findById(transaction.id()).orElse(null);
        if (searchTransaction != null) {
            throw new TransactionAlreadyExistException();
        }

        return saveTransactionUseCaseOut.save(transaction);
    }
}
