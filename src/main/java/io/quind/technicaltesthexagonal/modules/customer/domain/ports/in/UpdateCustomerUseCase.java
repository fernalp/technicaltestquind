package io.quind.technicaltesthexagonal.modules.customer.domain.ports.in;

import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;

import java.util.Optional;

public interface UpdateCustomerUseCase {
    Optional<CustomerResponse> updateCustomer(Long id, CustomerRequest customerRequest);
}
