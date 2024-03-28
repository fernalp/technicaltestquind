package io.quind.technicaltesthexagonal.modules.customer.domain.ports.in;

import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;

import java.util.Optional;

public interface CreateCustomerUseCase {

    Optional<CustomerResponse> createCustomer(CustomerRequest customerRequest);
}
