package io.quind.technicaltesthexagonal.modules.transaction.application.services;

import io.quind.technicaltesthexagonal.modules.transaction.domain.dtos.TransactionRequest;
import io.quind.technicaltesthexagonal.modules.transaction.domain.dtos.TransactionResponse;
import io.quind.technicaltesthexagonal.modules.transaction.domain.ports.in.CreateTransactionUseCase;

public class TransactionService {

    private final CreateTransactionUseCase createTransactionUseCase;

    public TransactionService(CreateTransactionUseCase createTransactionUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
    }

    public TransactionResponse createTransaction(TransactionRequest transactionRequest){
        return createTransactionUseCase.createTransaction(transactionRequest);
    }
}
