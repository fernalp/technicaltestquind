package io.quind.technicaltesthexagonal.customer.application.usecases;

import io.quind.technicaltesthexagonal.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.customer.domain.ports.in.RetrieveCustomerUseCase;
import io.quind.technicaltesthexagonal.customer.domain.ports.out.CustomerRepositoryPort;

import java.util.List;
import java.util.Optional;

public class RetrieveCustomerUseCaseImpl implements RetrieveCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public RetrieveCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Optional<Customer> getCustomer(Long id) {
        return customerRepositoryPort.findById(id);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepositoryPort.findAll();
    }
}
