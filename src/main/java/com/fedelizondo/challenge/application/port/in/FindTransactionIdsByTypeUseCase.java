package com.fedelizondo.challenge.application.port.in;

import com.fedelizondo.challenge.dominio.model.Transaction;

import java.util.List;

@FunctionalInterface
public interface FindTransactionIdsByTypeUseCase {
    List<Long> findTransactionByType(String type);
}
