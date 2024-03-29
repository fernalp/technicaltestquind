package io.quind.technicaltesthexagonal.modules.customer.domain.ports.in;

import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;

public interface UpdateCustomerUseCase {
    CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest);
}
