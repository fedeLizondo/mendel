package com.fedelizondo.challenge.infrastructure.adapter.out.persistence.memory.repository;

import com.fedelizondo.challenge.application.port.out.FindByIdTransactionUseCaseOut;
import com.fedelizondo.challenge.application.port.out.SaveTransactionUseCaseOut;
import com.fedelizondo.challenge.dominio.model.Transaction;
import com.fedelizondo.challenge.infrastructure.adapter.out.persistence.memory.entity.TransactionEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransactionRepository implements FindByIdTransactionUseCaseOut, SaveTransactionUseCaseOut {

    private static final ConcurrentHashMap<Long, TransactionEntity> transactions = new ConcurrentHashMap<>();

    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.ofNullable (transactions.get(id)).map(entity ->
                new Transaction(id, entity.getAmount(), entity.getType(), entity.getParentId())
        );
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionEntity = new TransactionEntity(transaction.id(), transaction.amount(), transaction.type(), transaction.parentId());
        transactions.putIfAbsent(transactionEntity.getTransactionId(), transactionEntity);
        return new Transaction(transactionEntity.getTransactionId(), transactionEntity.getAmount(), transactionEntity.getType(), transactionEntity.getParentId());
    }

    public static void clearData(){
        transactions.clear();
    }
}
