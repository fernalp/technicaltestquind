package io.quind.technicaltesthexagonal.modules.customer.infrastructure.repositories;

import io.quind.technicaltesthexagonal.modules.customer.infrastructure.entities.CustomerEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, Long> {


    @Transactional
    @Modifying()
    @Query("UPDATE CustomerEntity ce SET ce.email = :email WHERE ce.customerId = :id")
    void update(@Param("id") long id,@Param("email") String email);

}
