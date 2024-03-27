package io.quind.technicaltesthexagonal.customer.domain.ports.in;

import io.quind.technicaltesthexagonal.customer.domain.models.Customer;

public interface CreateCustomerUseCase {

    Customer createCustomer(Customer customer);
}
