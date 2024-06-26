package io.quind.technicaltesthexagonal.modules.customer.infrastructure.repositories;

import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;
import io.quind.technicaltesthexagonal.modules.customer.infrastructure.entities.CustomerEntity;
import io.quind.technicaltesthexagonal.modules.customer.infrastructure.mappers.CustomerEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaCustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final JpaCustomerRepository jpaCustomerRepository;

    public JpaCustomerRepositoryAdapter(JpaCustomerRepository jpaCustomerRepository) {
        this.jpaCustomerRepository = jpaCustomerRepository;
    }


    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = CustomerEntityMapper.fromCustomer(customer);
        return CustomerEntityMapper.toCustomer(jpaCustomerRepository.save(customerEntity));
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return jpaCustomerRepository.findById(id).map(CustomerEntityMapper::toCustomer);
    }

    @Override
    public List<Customer> findAll() {
        return jpaCustomerRepository.findAll().stream().map(CustomerEntityMapper::toCustomer).collect(Collectors.toList());
    }


    @Override
    public void deleteById(Long id) {
        jpaCustomerRepository.deleteById(id);
    }

    @Override
    public boolean existEmail(String email) {
        return jpaCustomerRepository.existsByEmail(email);
    }

    @Override
    public boolean existIdNumber(String idNumber) {
        return jpaCustomerRepository.existsByIdNumber(idNumber);
    }
}
