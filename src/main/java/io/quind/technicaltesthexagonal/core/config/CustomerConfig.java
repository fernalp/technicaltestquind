package io.quind.technicaltesthexagonal.core.config;

import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;
import io.quind.technicaltesthexagonal.modules.customer.application.services.CustomerService;
import io.quind.technicaltesthexagonal.modules.customer.application.usecases.CreateCustomerUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.customer.application.usecases.DeleteCustomerUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.customer.application.usecases.RetrieveCustomerUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.customer.application.usecases.UpdateCustomerUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;
import io.quind.technicaltesthexagonal.modules.customer.infrastructure.repositories.JpaCustomerRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    public CustomerService customerService(CustomerRepositoryPort customerRepositoryPort, AccountRepositoryPort accountRepositoryPort){
        return new CustomerService(
                new CreateCustomerUseCaseImpl(customerRepositoryPort),
                new RetrieveCustomerUseCaseImpl(customerRepositoryPort),
                new UpdateCustomerUseCaseImpl(customerRepositoryPort),
                new DeleteCustomerUseCaseImpl(customerRepositoryPort, accountRepositoryPort)
        );
    }

    @Bean
    public CustomerRepositoryPort customerRepositoryPort(JpaCustomerRepositoryAdapter jpaCustomerRepositoryAdapter){
        return jpaCustomerRepositoryAdapter;
    }
}
