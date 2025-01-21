package com.fedelizondo.challenge.infrastructure.adapter.in.rest.controller;

import com.fedelizondo.challenge.application.port.in.CreateTransactionUseCase;
import com.fedelizondo.challenge.application.port.in.FindTransactionIdsByTypeUseCase;
import com.fedelizondo.challenge.dominio.model.Transaction;
import com.fedelizondo.challenge.infrastructure.adapter.in.rest.request.TransactionRequest;
import com.fedelizondo.challenge.infrastructure.adapter.in.rest.response.TransactionResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final CreateTransactionUseCase createTransactionUseCase;
    private final FindTransactionIdsByTypeUseCase findTransactionIdsByTypeUseCase;

    public TransactionController(CreateTransactionUseCase createTransactionUseCase, FindTransactionIdsByTypeUseCase findTransactionIdsByTypeUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
        this.findTransactionIdsByTypeUseCase = findTransactionIdsByTypeUseCase;
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> saveTransaction(@PathVariable long transactionId, @Valid @RequestBody TransactionRequest transactionRequest) {

        Transaction transaction = new Transaction(
                transactionId,
                transactionRequest.getAmount(),
                transactionRequest.getType(),
                transactionRequest.getParentId()
        );

        Transaction savedTransaction = createTransactionUseCase.createTransaction(transaction);

        return ResponseEntity.ok(
                new TransactionResponse(
                        savedTransaction.id(),
                        savedTransaction.amount(),
                        savedTransaction.type(),
                        savedTransaction.parentId())
        );
    }

    @GetMapping("/types/{type}")
    public ResponseEntity<List<Long>> findTransactionByType(@NotBlank @PathVariable String type) {
        return ResponseEntity.ok(findTransactionIdsByTypeUseCase.findTransactionByType(type.toLowerCase(Locale.ROOT)));
    }
}
