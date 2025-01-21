package com.fedelizondo.challenge.infrastructure.adapter.in.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse {
    @JsonProperty("transaction_id")
    private final long transactionId;
    private final double amount;
    private final String type;
    @JsonProperty("parent_id")
    private final Long parentId;

    public TransactionResponse(long transactionId, double amount, String type, Long parentId) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
        this.parentId = parentId;
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
}
