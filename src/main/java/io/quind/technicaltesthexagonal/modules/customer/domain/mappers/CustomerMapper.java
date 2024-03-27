package io.quind.technicaltesthexagonal.modules.customer.domain.mappers;

import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;

public class CustomerMapper {

    public static Customer fromCustomerRequest(CustomerRequest customerRequest){
        return Customer.builder()
                .idType(customerRequest.getTipoIdentificacion())
                .idNumber(customerRequest.getNumeroIdentificacion())
                .firstname(customerRequest.getNombres())
                .lastname(customerRequest.getApellidos())
                .email(customerRequest.getCorreoElectronico())
                .birthdate(customerRequest.getFechaNacimiento())
                .build();
    }

    public static CustomerResponse toCustomerResponse(Customer customer){
        return (CustomerResponse) CustomerResponse.builder()
                .id(customer.getCustomerId())
                .tipoIdentificacion(customer.getIdType())
                .numeroIdentificacion(customer.getIdNumber())
                .nombres(customer.getFirstname())
                .apellidos(customer.getLastname())
                .correoElectronico(customer.getEmail())
                .fechaNacimiento(customer.getBirthdate())
                .build();
    }
}
