package com.fedelizondo.challenge.infrastructure.adapter.out.persistence.memory.entity;

public class TransactionEntity {
    private long transactionId;
    private double amount;
    private String type;
    private Long parentId;
    private double total;

    public TransactionEntity(long transactionId, double amount, String type, Long parentId) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
        this.parentId = parentId;
        this.total = amount;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Long getParentId() {
        return parentId;
    }

    public double getTotal() {
        return total;
    }

    public synchronized void addTotal(double amount) {
            total += amount;
    }
}
