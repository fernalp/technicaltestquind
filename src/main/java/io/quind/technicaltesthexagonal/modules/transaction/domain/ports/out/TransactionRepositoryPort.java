package io.quind.technicaltesthexagonal.modules.transaction.domain.ports.out;

import io.quind.technicaltesthexagonal.modules.transaction.domain.models.Transaction;

public interface TransactionRepositoryPort {

    Transaction createTransaction(Transaction transaction);
}
