package io.quind.technicaltesthexagonal.modules.customer.application.usecases;

import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.DeleteCustomerUseCase;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;

public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public DeleteCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public boolean deleteCustomer(Long id) {
        return customerRepositoryPort.deleteById(id);
    }
}
