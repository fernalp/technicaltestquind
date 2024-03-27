package io.quind.technicaltesthexagonal.customer.domain.ports.in;

import io.quind.technicaltesthexagonal.customer.domain.models.Customer;

import java.util.Optional;

public interface UpdateCustomerUseCase {
    Optional<Customer> updateCustomer(Long id,Customer customer);
}
