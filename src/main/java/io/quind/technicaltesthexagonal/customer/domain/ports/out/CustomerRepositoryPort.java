package io.quind.technicaltesthexagonal.customer.domain.ports.out;

import io.quind.technicaltesthexagonal.customer.domain.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {

    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    Optional<Customer> update(Customer customer);
    boolean deleteById(Long id);

}
