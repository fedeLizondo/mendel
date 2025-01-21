package com.fedelizondo.challenge.application.port.in;

@FunctionalInterface
public interface GetCumulativeSumTransactionUseCase {
    double getCumulativeSumForTransaction(Long id);
}
