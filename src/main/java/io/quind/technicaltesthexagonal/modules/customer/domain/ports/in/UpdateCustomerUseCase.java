package io.quind.technicaltesthexagonal.modules.customer.domain.ports.in;

import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;

public interface UpdateCustomerUseCase {
    CustomerResponse updateCustomer(Long id, Customer customer);
}
