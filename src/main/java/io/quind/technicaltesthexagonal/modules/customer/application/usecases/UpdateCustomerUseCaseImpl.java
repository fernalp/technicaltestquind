package io.quind.technicaltesthexagonal.modules.customer.application.usecases;

import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.mappers.CustomerMapper;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.UpdateCustomerUseCase;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;

public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public UpdateCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public CustomerResponse updateCustomer(Long id, Customer customer) {
        return CustomerMapper.toCustomerResponse(customerRepositoryPort.update(customer));
    }
}
