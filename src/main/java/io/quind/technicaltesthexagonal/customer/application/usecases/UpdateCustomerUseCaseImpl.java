package io.quind.technicaltesthexagonal.customer.application.usecases;

import io.quind.technicaltesthexagonal.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.customer.domain.ports.in.UpdateCustomerUseCase;
import io.quind.technicaltesthexagonal.customer.domain.ports.out.CustomerRepositoryPort;

import java.util.Optional;

public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public UpdateCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Optional<Customer> updateCustomer(Long id, Customer customer) {
        return customerRepositoryPort.update(customer);
    }
}
