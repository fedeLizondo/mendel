package com.fedelizondo.challenge.application.port.out;

import com.fedelizondo.challenge.dominio.model.Transaction;

import java.util.List;

public interface FindByTypeTransactionsUseCaseOut {
    List<Transaction> findTransactionByType(String type);
}
