package io.quind.technicaltesthexagonal.modules.customer.infrastructure.repositories;

import io.quind.technicaltesthexagonal.modules.customer.infrastructure.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByEmail(String email);
    boolean existsByIdNumber(String idNumber);

}
