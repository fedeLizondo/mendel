package com.fedelizondo.challenge.application.service;

import com.fedelizondo.challenge.application.port.in.FindTransactionIdsByTypeUseCase;
import com.fedelizondo.challenge.application.port.out.FindByTypeTransactionsUseCaseOut;
import com.fedelizondo.challenge.dominio.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindTransactionIdsByTypeUseCaseImpl implements FindTransactionIdsByTypeUseCase {

    private final FindByTypeTransactionsUseCaseOut findByTypeTransactionsUseCaseOut;

    public FindTransactionIdsByTypeUseCaseImpl(FindByTypeTransactionsUseCaseOut findByTypeTransactionsUseCaseOut) {
        this.findByTypeTransactionsUseCaseOut = findByTypeTransactionsUseCaseOut;
    }

    @Override
    public List<Long> findTransactionByType(String type) {
        return findByTypeTransactionsUseCaseOut.findTransactionByType(type)
                .stream()
                .map(Transaction::id)
                .toList();
    }
}
