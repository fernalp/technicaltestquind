package io.quind.technicaltesthexagonal.config;

import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;
import io.quind.technicaltesthexagonal.modules.transaction.application.services.TransactionService;
import io.quind.technicaltesthexagonal.modules.transaction.application.usecases.CreateTransactionUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.transaction.domain.ports.out.TransactionRepositoryPort;
import io.quind.technicaltesthexagonal.modules.transaction.infrastructure.repositories.JpaTransactionRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfig {

    @Bean
    public TransactionService transactionService(TransactionRepositoryPort transactionRepositoryPort, AccountRepositoryPort accountRepositoryPort){
        return new TransactionService(
                new CreateTransactionUseCaseImpl(transactionRepositoryPort, accountRepositoryPort)
        );
    }

    @Bean
    public TransactionRepositoryPort transactionRepositoryPort(JpaTransactionRepositoryAdapter transactionRepositoryAdapter){
        return transactionRepositoryAdapter;
    }
}
