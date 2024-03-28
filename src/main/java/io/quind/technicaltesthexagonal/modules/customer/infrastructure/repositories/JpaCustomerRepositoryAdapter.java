package io.quind.technicaltesthexagonal.modules.customer.infrastructure.repositories;

import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;
import io.quind.technicaltesthexagonal.modules.customer.infrastructure.entities.CustomerEntity;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.IdType;
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
    public Customer update(Long id, Customer customer) {
        CustomerEntity customerEntity = jpaCustomerRepository.findById(id).get();
        customerEntity.setBirthdate(customer.getBirthdate());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setFirstname(customer.getFirstname());
        customerEntity.setLastname(customerEntity.getLastname());
        customerEntity.setIdNumber(customer.getIdNumber());
        customerEntity.setIdType(customer.getIdType());
        jpaCustomerRepository.save(customerEntity);
        return CustomerEntityMapper.toCustomer(customerEntity);
    }

    @Override
    public boolean deleteById(Long id) {
        if (jpaCustomerRepository.existsById(id)){
            jpaCustomerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
