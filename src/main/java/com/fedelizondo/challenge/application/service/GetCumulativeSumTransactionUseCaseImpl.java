package com.fedelizondo.challenge.application.service;

import com.fedelizondo.challenge.application.port.in.GetCumulativeSumTransactionUseCase;
import com.fedelizondo.challenge.application.port.out.FindByIdTransactionUseCaseOut;
import com.fedelizondo.challenge.application.port.out.GetCumulativeSumForTransactionUseCaseOut;
import com.fedelizondo.challenge.dominio.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class GetCumulativeSumTransactionUseCaseImpl implements GetCumulativeSumTransactionUseCase {

    private final GetCumulativeSumForTransactionUseCaseOut getCumulativeSumForTransactionUseCaseOut;
    private final FindByIdTransactionUseCaseOut findByIdTransactionUseCaseOut;

    public GetCumulativeSumTransactionUseCaseImpl(
            GetCumulativeSumForTransactionUseCaseOut getCumulativeSumForTransactionUseCaseOut,
            FindByIdTransactionUseCaseOut findByIdTransactionUseCaseOut
    ) {
        this.getCumulativeSumForTransactionUseCaseOut = getCumulativeSumForTransactionUseCaseOut;
        this.findByIdTransactionUseCaseOut = findByIdTransactionUseCaseOut;
    }

    @Override
    public double getCumulativeSumForTransaction(Long id) {
        Transaction transaction = findByIdTransactionUseCaseOut.findById(id).orElse(null);
        if(transaction == null ) {
            return 0;
        }
        return getCumulativeSumForTransactionUseCaseOut.getCumulativeSumForTransaction(id);
    }
}
