package io.quind.technicaltesthexagonal.modules.customer.application.usecases;

import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.DeleteCustomerUseCase;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;

public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;
    private final AccountRepositoryPort accountRepositoryPort;

    public DeleteCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort, AccountRepositoryPort accountRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public boolean deleteCustomer(Long id) {
        if (!customerRepositoryPort.existById(id)){
            throw new IllegalArgumentException("There is no client with that id");
        }
        return customerRepositoryPort.deleteById(id);
    }
}
