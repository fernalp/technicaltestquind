package io.quind.technicaltesthexagonal.modules.customer.infrastructure.mappers;

import io.quind.technicaltesthexagonal.modules.account.infrastructure.mappers.AccountEntityMapper;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.customer.infrastructure.entities.CustomerEntity;

import java.util.stream.Collectors;


public class CustomerEntityMapper {

    public static CustomerEntity fromCustomer(Customer customer){
        return CustomerEntity.builder()
                .customerId(customer.getId())
                .idType(customer.getIdType())
                .idNumber(customer.getIdNumber())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .birthdate(customer.getDateOfBirth())
                .build();
    }
    public static Customer toCustomer(CustomerEntity customerEntity){
        return Customer.builder()
                .id(customerEntity.getCustomerId())
                .idType(customerEntity.getIdType())
                .idNumber(customerEntity.getIdNumber())
                .firstname(customerEntity.getFirstname())
                .lastname(customerEntity.getLastname())
                .email(customerEntity.getEmail())
                .dateOfBirth(customerEntity.getBirthdate())
                .createdAt(customerEntity.getCreatedAt())
                .updatedAt(customerEntity.getUpdatedAt())
                .build();
    }

}
