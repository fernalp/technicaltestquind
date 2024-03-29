package io.quind.technicaltesthexagonal.modules.transaction.infrastructure.repositories;

import io.quind.technicaltesthexagonal.modules.transaction.domain.models.Transaction;
import io.quind.technicaltesthexagonal.modules.transaction.domain.ports.out.TransactionRepositoryPort;
import io.quind.technicaltesthexagonal.modules.transaction.infrastructure.entities.TransactionEntity;
import io.quind.technicaltesthexagonal.modules.transaction.infrastructure.mappers.TransactionEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class JpaTransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final JpaTransactionRepository jpaTransactionRepository;

    public JpaTransactionRepositoryAdapter(JpaTransactionRepository jpaTransactionRepository) {
        this.jpaTransactionRepository = jpaTransactionRepository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        TransactionEntity transactionEntity = TransactionEntityMapper.fromTransaction(transaction);
        return TransactionEntityMapper.toTransaction(jpaTransactionRepository.save(transactionEntity));
    }
}
