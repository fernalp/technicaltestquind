package io.quind.technicaltesthexagonal.modules.customer.application.usecases;

import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.mappers.CustomerMapper;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.RetrieveCustomerUseCase;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RetrieveCustomerUseCaseImpl implements RetrieveCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public RetrieveCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Optional<CustomerResponse> getCustomer(Long id) {
        return customerRepositoryPort.findById(id).map(CustomerMapper::toCustomerResponse);
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        return customerRepositoryPort.findAll().stream().map(CustomerMapper::toCustomerResponse).collect(Collectors.toList());
    }
}
