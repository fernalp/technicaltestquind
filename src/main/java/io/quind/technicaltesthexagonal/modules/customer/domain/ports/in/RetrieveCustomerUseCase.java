package io.quind.technicaltesthexagonal.modules.customer.domain.ports.in;

import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;

import java.util.List;
import java.util.Optional;

public interface RetrieveCustomerUseCase {

    Optional<CustomerResponse> getCustomer(Long id);
    List<CustomerResponse> getAllCustomer();
}
