package com.fedelizondo.challenge.infrastructure.adapter.out.persistence.memory.repository;

import com.fedelizondo.challenge.application.port.out.FindByIdTransactionUseCaseOut;
import com.fedelizondo.challenge.application.port.out.FindByTypeTransactionsUseCaseOut;
import com.fedelizondo.challenge.application.port.out.GetCumulativeSumForTransactionUseCaseOut;
import com.fedelizondo.challenge.application.port.out.SaveTransactionUseCaseOut;
import com.fedelizondo.challenge.dominio.model.Transaction;
import com.fedelizondo.challenge.infrastructure.adapter.out.persistence.memory.entity.TransactionEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransactionRepository implements
        FindByIdTransactionUseCaseOut,
        SaveTransactionUseCaseOut,
        FindByTypeTransactionsUseCaseOut,
        GetCumulativeSumForTransactionUseCaseOut {

    private static final ConcurrentHashMap<Long, TransactionEntity> transactions = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, List<TransactionEntity>> types = new ConcurrentHashMap<>();

    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.ofNullable(transactions.get(id)).map(entity ->
                new Transaction(id, entity.getAmount(), entity.getType(), entity.getParentId())
        );
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionEntity = new TransactionEntity(transaction.id(), transaction.amount(), transaction.type(), transaction.parentId());
        saveTransactionEntity(transactionEntity);
        saveType(transaction.type(), transactionEntity);
        return new Transaction(transactionEntity.getTransactionId(), transactionEntity.getAmount(), transactionEntity.getType(), transactionEntity.getParentId());
    }

    @Override
    public List<Transaction> findTransactionByType(String type) {
        return types.getOrDefault(type, List.of()).stream().filter(Objects::nonNull).map(entity ->
                        new Transaction(
                                entity.getTransactionId(),
                                entity.getAmount(),
                                entity.getType(),
                                entity.getParentId()
                        ))
                .toList();
    }

    @Override
    public double getCumulativeSumForTransaction(Long transactionId) {
        return transactions.get(transactionId).getTotal();
    }

    private static void saveType(String type, TransactionEntity entity) {
        types.putIfAbsent(type, new ArrayList<>());
        types.get(type).add(entity);
    }

    private static void saveTransactionEntity(TransactionEntity transaction) {
        transactions.putIfAbsent(transaction.getTransactionId(), transaction);
        updateAmountInParents(transaction.getParentId(), transaction.getAmount());
    }

    private static void updateAmountInParents(Long parent, double amount) {
        while (parent != null && transactions.containsKey(parent)) {
            TransactionEntity parentEntity = transactions.get(parent);
            parentEntity.addTotal(amount);
            parent = parentEntity.getParentId();
        }
    }

    public static void clearData() {
        transactions.clear();
        types.clear();
    }
}
