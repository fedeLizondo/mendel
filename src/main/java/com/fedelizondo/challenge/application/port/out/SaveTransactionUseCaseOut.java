package com.fedelizondo.challenge.application.port.out;

import com.fedelizondo.challenge.dominio.model.Transaction;

public interface SaveTransactionUseCaseOut {
    Transaction save(Transaction transaction);
}
