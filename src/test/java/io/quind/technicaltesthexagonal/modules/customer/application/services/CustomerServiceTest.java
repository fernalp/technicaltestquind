package io.quind.technicaltesthexagonal.modules.customer.application.services;

import io.quind.technicaltesthexagonal.modules.customer.application.usecases.CreateCustomerUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.customer.application.usecases.DeleteCustomerUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.customer.application.usecases.RetrieveCustomerUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.customer.application.usecases.UpdateCustomerUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.IdType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CreateCustomerUseCaseImpl createCustomerUseCase;
    @Mock
    private RetrieveCustomerUseCaseImpl retrieveCustomerUseCase;
    @Mock
    private UpdateCustomerUseCaseImpl updateCustomerUseCase;
    @Mock
    private DeleteCustomerUseCaseImpl deleteCustomerUseCase;

    CustomerResponse customerResponse;
    CustomerRequest customerRequest;
    @BeforeEach
    void setUp() {
        customerResponse = CustomerResponse.builder()
                .id(1L)
                .idType(IdType.CC)
                .idNumber("1020304050")
                .firstname("John")
                .lastname("McDonald")
                .email("johnmcdonald@gmail.com")
                .dateOfBirth(LocalDate.of(1990,12,12))
                .build();

        customerRequest = CustomerRequest.builder()
                .idType("CC")
                .idNumber("1020304050")
                .firstname("John")
                .lastname("McDonald")
                .email("johnmcdonald@gmail.com")
                .dateOfBirth(LocalDate.of(1990,12,12))
                .build();
    }

    @Test
    void customerService_CreateCustomer_ReturnCustomerResponse() {
        when(createCustomerUseCase.createCustomer(Mockito.any(CustomerRequest.class))).thenReturn(customerResponse);
        CustomerResponse savedCustomer = customerService.createCustomer(customerRequest);
        assertThat(savedCustomer).isNotNull();
    }
    @Test
    void customerService_DeleteCustomer_UseCaseDeleteCustomer() {
        customerService.deleteCustomer(1L);
        verify(deleteCustomerUseCase, times(1)).deleteCustomer(1L);
    }

    @Test
    void customerService_GetCustomerById_ReturnCustomerResponse() {
        when(retrieveCustomerUseCase.getCustomerById(Mockito.anyLong())).thenReturn(Optional.of(customerResponse));
        Optional<CustomerResponse> response = customerService.getCustomerById(1L);
        assertThat(response).contains(customerResponse);
        assertTrue(response.isPresent());
    }

    @Test
    void customerService_GetCustomerById_ReturnEmpty() {
        when(retrieveCustomerUseCase.getCustomerById(Mockito.anyLong())).thenReturn(Optional.empty());
        Optional<CustomerResponse> response = customerService.getCustomerById(1L);
        assertTrue(response.isEmpty());
    }

    @Test
    void customerService_getAllCustomer_ReturnCustomerResponses() {
        when(retrieveCustomerUseCase.getAllCustomer()).thenReturn(List.of(CustomerResponse.builder().build(), CustomerResponse.builder().build()));
        List<CustomerResponse> customerResponses = customerService.getAllCustomer();
        assertThat(customerResponses).isNotNull().hasSize(2);
    }

    @Test
    void customerService_getAllCustomer_ReturnListEmpty() {
        when(retrieveCustomerUseCase.getAllCustomer()).thenReturn(List.of());
        List<CustomerResponse> customerResponses = customerService.getAllCustomer();
        assertThat(customerResponses).isEmpty();
    }

    @Test
    void updateCustomer() {
        when(updateCustomerUseCase.updateCustomer(Mockito.anyLong(), Mockito.any(CustomerRequest.class))).thenReturn(customerResponse);
        CustomerResponse response = customerService.updateCustomer(1L, customerRequest);
        assertThat(response).isInstanceOf(CustomerResponse.class).isNotNull();
    }
}