package io.quind.technicaltesthexagonal.modules.customer.domain.mappers;

import io.quind.technicaltesthexagonal.modules.account.domain.mappers.AccountMapper;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;

import java.util.stream.Collectors;

public class CustomerMapper {

    public static Customer fromCustomerRequest(CustomerRequest customerRequest){
        return Customer.builder()
                .idType(customerRequest.getIdType())
                .idNumber(customerRequest.getIdNumber())
                .firstname(customerRequest.getFirstname().trim().toLowerCase())
                .lastname(customerRequest.getLastname().trim().toLowerCase())
                .email(customerRequest.getEmail().trim().toLowerCase())
                .dateOfBirth(customerRequest.getDateOfBirth())
                .build();
    }

    public static CustomerResponse toCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .idType(customer.getIdType())
                .idNumber(customer.getIdNumber())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .dateOfBirth(customer.getDateOfBirth())
                .build();
    }
}
