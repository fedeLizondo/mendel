package com.fedelizondo.challenge.application.port.out;

import com.fedelizondo.challenge.dominio.model.Transaction;

import java.util.Optional;

public interface FindByIdTransactionUseCaseOut {
    Optional<Transaction> findById(Long id);
}
