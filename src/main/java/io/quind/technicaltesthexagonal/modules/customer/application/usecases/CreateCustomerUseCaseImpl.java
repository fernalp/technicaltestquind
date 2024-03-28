package io.quind.technicaltesthexagonal.modules.customer.application.usecases;

import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.mappers.CustomerMapper;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.CreateCustomerUseCase;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;

import java.util.Optional;

public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public CreateCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Optional<CustomerResponse> createCustomer(CustomerRequest customerRequest) {
            Customer customer = CustomerMapper.fromCustomerRequest(customerRequest);
            return Optional.ofNullable(customerRepositoryPort.save(customer)).map(CustomerMapper::toCustomerResponse);
    }



}
