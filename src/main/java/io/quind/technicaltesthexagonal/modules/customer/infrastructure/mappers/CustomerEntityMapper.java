package io.quind.technicaltesthexagonal.modules.customer.infrastructure.mappers;

import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.customer.infrastructure.entities.CustomerEntity;
import io.quind.technicaltesthexagonal.modules.customer.infrastructure.entities.IdType;

public class CustomerEntityMapper {

    public static CustomerEntity fromCustomer(Customer customer){
        return CustomerEntity.builder()
                .idType(IdType.valueOf(customer.getIdType()))
                .idNumber(customer.getIdNumber())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .birthdate(customer.getBirthdate())
                .build();
    }
    public static Customer toCustomer(CustomerEntity customerEntity){
        return Customer.builder()
                .customerId(customerEntity.getCustomerId())
                .idType(customerEntity.getIdType().getDescription())
                .idNumber(customerEntity.getIdNumber())
                .firstname(customerEntity.getFirstname())
                .lastname(customerEntity.getLastname())
                .email(customerEntity.getEmail())
                .birthdate(customerEntity.getBirthdate())
                .createdAt(customerEntity.getCreatedAt())
                .updatedAt(customerEntity.getUpdatedAt())
                .build();
    }

}
