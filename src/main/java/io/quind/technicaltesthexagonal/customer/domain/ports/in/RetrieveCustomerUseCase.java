package io.quind.technicaltesthexagonal.customer.domain.ports.in;

import io.quind.technicaltesthexagonal.customer.domain.models.Customer;

import java.util.List;
import java.util.Optional;

public interface RetrieveCustomerUseCase {

    Optional<Customer> getCustomer(Long id);
    List<Customer> getAllCustomer();
}
