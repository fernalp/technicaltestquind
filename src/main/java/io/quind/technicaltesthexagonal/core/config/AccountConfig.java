package io.quind.technicaltesthexagonal.core.config;

import io.quind.technicaltesthexagonal.modules.account.application.services.AccountService;
import io.quind.technicaltesthexagonal.modules.account.application.usecases.CreateAccountUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.account.application.usecases.DeleteAccountUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.account.application.usecases.RetrieveAccountUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.account.application.usecases.UpdateAccountUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;
import io.quind.technicaltesthexagonal.modules.account.infrastructure.repositories.JpaAccountRepositoryAdapter;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfig {

    @Bean
    public AccountService accountService(AccountRepositoryPort accountRepositoryPort, CustomerRepositoryPort customerRepositoryPort){
        return new AccountService(
                new CreateAccountUseCaseImpl(accountRepositoryPort, customerRepositoryPort),
                new RetrieveAccountUseCaseImpl(accountRepositoryPort),
                new UpdateAccountUseCaseImpl(accountRepositoryPort),
                new DeleteAccountUseCaseImpl(accountRepositoryPort)
        );
    }

    @Bean
    public AccountRepositoryPort accountRepositoryPort(JpaAccountRepositoryAdapter jpaAccountRepositoryAdapter){
        return jpaAccountRepositoryAdapter;
    }
}
