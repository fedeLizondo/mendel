package com.fedelizondo.challenge.infrastructure.adapter.in.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TransactionRequest {
    @Min(0)
    private final double amount;

    @NotBlank
    private final String type;

    @JsonProperty("parent_id")
    private final Long parentId;

    public TransactionRequest(double amount, String type, Long parentId) {
        this.amount = amount;
        this.type = type;
        this.parentId = parentId;
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
