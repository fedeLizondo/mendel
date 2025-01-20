package com.fedelizondo.challenge.dominio.model;

public record Transaction(Long id, double amount, String type, Long ParentId) {
}
