package io.quind.technicaltesthexagonal.modules.customer.domain.ports.out;

import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {

    Customer save(Customer customer);
    boolean existById(Long id);
    boolean existEmail(String email);
    boolean existIdNumber(String idNumber);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    boolean deleteById(Long id);

}
