package io.quind.technicaltesthexagonal.modules.customer.application.usecases;

import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.IdType;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveCustomerUseCaseImplTest {

    @Mock
    private CustomerRepositoryPort customerRepositoryPort;

    @InjectMocks
    private RetrieveCustomerUseCaseImpl retrieveCustomerUseCase;

    List<Customer> customers;
    Long id = 1L;

    @BeforeEach
    void setUp() {
        customers = List.of(
                Customer.builder()
                        .id(1L)
                        .idType(IdType.CC)
                        .idNumber("1020304050")
                        .firstname("John")
                        .lastname("McDonald")
                        .email("johnmcdonald@gmail.com")
                        .dateOfBirth(LocalDate.of(1990,12,12))
                        .build(),
                Customer.builder()
                        .id(2L)
                        .idType(IdType.PA)
                        .idNumber("1122334455")
                        .firstname("Xavier")
                        .lastname("James")
                        .email("xavierjames@gmail.com")
                        .dateOfBirth(LocalDate.of(2000,5,21))
                        .build()
        );
    }

    @Test
    void useCase_GetCustomerById_ReturnCustomerResponse() {

        when(customerRepositoryPort.findById(id)).thenReturn(customers.stream().filter(customer -> customer.getId().equals(id)).findFirst());
        Optional<CustomerResponse> customerResponse = retrieveCustomerUseCase.getCustomerById(id);
        assertTrue(customerResponse.isPresent());
        assertEquals(customerResponse.get().getFirstname(), customers.get(Math.toIntExact(id-1)).getFirstname());

        id =2L;
        when(customerRepositoryPort.findById(id)).thenReturn(customers.stream().filter(customer -> customer.getId().equals(id)).findFirst());
        Optional<CustomerResponse> customerResponse2 = retrieveCustomerUseCase.getCustomerById(id);
        assertTrue(customerResponse2.isPresent());
        assertEquals(customerResponse2.get().getFirstname(), customers.get(Math.toIntExact(id-1)).getFirstname());

    }

    @Test
    void useCase_GetCustomerById_ReturnEmpty(){
        id = 5L;
        when(customerRepositoryPort.findById(id)).thenReturn(customers.stream().filter(customer -> customer.getId().equals(id)).findFirst());
        Optional<CustomerResponse> customerResponse = retrieveCustomerUseCase.getCustomerById(id);
        assertTrue(customerResponse.isEmpty());
    }

    @Test
    void useCase_GetAllCustomer_ReturnCustomerResponses() {
        when(customerRepositoryPort.findAll()).thenReturn(customers);
        List<CustomerResponse> customerResponses = retrieveCustomerUseCase.getAllCustomer();
        assertThat(customerResponses).isNotNull().hasSize(2);
    }

    @Test
    void useCase_GetAllCustomer_ReturnListEmpty() {
        when(customerRepositoryPort.findAll()).thenReturn(List.of());
        List<CustomerResponse> customerResponsesEmpty = retrieveCustomerUseCase.getAllCustomer();
        assertThat(customerResponsesEmpty).isEmpty();
    }
}