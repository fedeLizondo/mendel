package com.fedelizondo.challenge.dominio.exception;

public class TransactionAlreadyExistException extends RuntimeException {
    public TransactionAlreadyExistException() {
        super("Transaction already exist");
    }
}
