package io.quind.technicaltesthexagonal.modules.transaction.domain.ports.in;

import io.quind.technicaltesthexagonal.modules.transaction.domain.dtos.TransactionRequest;
import io.quind.technicaltesthexagonal.modules.transaction.domain.dtos.TransactionResponse;

public interface CreateTransactionUseCase {

    TransactionResponse createTransaction(TransactionRequest transactionRequest);

}
