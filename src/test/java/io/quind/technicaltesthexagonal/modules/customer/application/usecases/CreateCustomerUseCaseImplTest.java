package io.quind.technicaltesthexagonal.modules.customer.application.usecases;

import io.quind.technicaltesthexagonal.modules.customer.application.exceptions.AlreadyExistException;
import io.quind.technicaltesthexagonal.modules.customer.application.exceptions.YoungerException;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.IdType;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseImplTest {

    @Mock
    private CustomerRepositoryPort customerRepositoryPort;
    @InjectMocks
    private CreateCustomerUseCaseImpl createCustomerUseCase;

    Customer customer = Customer.builder().build();
    CustomerRequest customerRequest = CustomerRequest.builder().build();

    @BeforeEach
    void setUp() {
        customer = Customer.builder()
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
    void useCase_createCustomer_ReturnCustomerResponse() {
        when(customerRepositoryPort.save(Mockito.any(Customer.class))).thenReturn(customer);
        CustomerResponse savedCustomer = createCustomerUseCase.createCustomer(customerRequest);
        assertThat(savedCustomer).isNotNull();
    }

    @Test
    void useCase_CreateCustomerYounger_ReturnThrow(){
        customerRequest.setDateOfBirth(LocalDate.of(2014,12,12));

        RuntimeException exception = assertThrows(YoungerException.class, () -> createCustomerUseCase.createCustomer(customerRequest));

        assertTrue(exception.getMessage().equalsIgnoreCase("The minimun age is 18 years old"));
    }

    @Test
    void useCase_CreateCustomerWithEmailExist_ReturnThrow(){

        when(customerRepositoryPort.existEmail(Mockito.anyString())).thenReturn(true);
        RuntimeException exception = assertThrows(AlreadyExistException.class, () -> createCustomerUseCase.createCustomer(customerRequest));
        assertTrue(exception.getMessage().equalsIgnoreCase("Email "+ customerRequest.getEmail() + " already exist!"));

    }

    @Test
    void useCase_CreateCustomerWithIdNumberExist_ReturnThrow(){

        when(customerRepositoryPort.existIdNumber(Mockito.anyString())).thenReturn(true);
        RuntimeException exception = assertThrows(AlreadyExistException.class, () -> createCustomerUseCase.createCustomer(customerRequest));
        assertTrue(exception.getMessage().equalsIgnoreCase("Identification Number "+ customerRequest.getIdNumber() + " already exist!"));

    }
}