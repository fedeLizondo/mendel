package com.fedelizondo.challenge.application.port.in;

import com.fedelizondo.challenge.dominio.model.Transaction;

@FunctionalInterface
public interface CreateTransactionUseCase {
    Transaction createTransaction(Transaction transaction);
}
