package io.quind.technicaltesthexagonal.modules.customer.application.usecases;

import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.mappers.CustomerMapper;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.UpdateCustomerUseCase;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;

import java.util.Optional;

public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public UpdateCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Optional<CustomerResponse > updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer customer = CustomerMapper.fromCustomerRequest(customerRequest);
        return Optional.ofNullable(CustomerMapper.toCustomerResponse(customerRepositoryPort.update(id, customer)));
    }
}
