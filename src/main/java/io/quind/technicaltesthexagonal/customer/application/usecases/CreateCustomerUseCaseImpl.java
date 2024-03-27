package io.quind.technicaltesthexagonal.customer.application.usecases;

import io.quind.technicaltesthexagonal.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.customer.domain.ports.in.CreateCustomerUseCase;
import io.quind.technicaltesthexagonal.customer.domain.ports.out.CustomerRepositoryPort;

public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public CreateCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepositoryPort.save(customer);
    }

}
