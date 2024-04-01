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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCustomerUseCaseImplTest {

    @Mock
    private CustomerRepositoryPort customerRepositoryPort;
    @InjectMocks
    private UpdateCustomerUseCaseImpl updateCustomerUseCase;

    Customer customer;
    CustomerRequest customerRequest;
    Long id;

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
    void useCase_UpdateCustomer_ReturnCustomerResponse() {
        id = 5L;
        customer.setId(id);
        when(customerRepositoryPort.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(customer));
        when(customerRepositoryPort.save(Mockito.any(Customer.class))).thenReturn(customer);

        CustomerResponse customerResponse = updateCustomerUseCase.updateCustomer(id, customerRequest);

        assertEquals(customerResponse.getId(), id);
        assertEquals(customerResponse.getIdType().name(), customerRequest.getIdType());
        assertEquals(customerResponse.getIdNumber(), customerRequest.getIdNumber());
        assertTrue(customerResponse.getFirstname().equalsIgnoreCase(customerRequest.getFirstname()));
        assertTrue(customerResponse.getLastname().equalsIgnoreCase(customerRequest.getLastname()));
        assertTrue(customerResponse.getEmail().equalsIgnoreCase(customerRequest.getEmail()));
    }

    @Test
    void useCase_UpdateCustomerWithIdInvalid_ReturnThrow(){
        id = 2L;
        when(customerRepositoryPort.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(customer));
        when(customerRepositoryPort.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception e = assertThrows(IllegalArgumentException.class, ()-> updateCustomerUseCase.updateCustomer(id, customerRequest));
        assertTrue(e.getMessage().equalsIgnoreCase("There is no client with the id"+ id));
    }

    @Test
    void useCase_UpdateCustomerWithDateOfBirthYounger_ReturnThrow(){
        id = 1L;
        customerRequest.setDateOfBirth(LocalDate.of(2018,12,12));
        when(customerRepositoryPort.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(customer));
        Exception e = assertThrows(YoungerException.class, () ->  updateCustomerUseCase.updateCustomer(id, customerRequest));
        assertTrue(e.getMessage().equalsIgnoreCase("The minimun age is 18 years old"));
    }

    @Test
    void useCase_UpdateCustomerWithIdNumberAlreadyExists_ReturnThrow(){
        id = 12L;
        customerRequest.setIdNumber("1234567890");
        when(customerRepositoryPort.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(customer));
        when(customerRepositoryPort.existIdNumber(Mockito.anyString())).thenReturn(true);
        Exception e = assertThrows(AlreadyExistException.class, ()-> updateCustomerUseCase.updateCustomer(id, customerRequest));
        assertTrue(e.getMessage().equalsIgnoreCase("Identification Number "+ customerRequest.getIdNumber() + " already exist!"));

    }

    @Test
    void useCase_UpdateCustomerWithEmailAlreadyExists_ReturnThrow(){
        customerRequest.setEmail("remove@back.com");
        when(customerRepositoryPort.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(customer));
        when(customerRepositoryPort.existEmail(Mockito.anyString())).thenReturn(true);
        Exception e = assertThrows(AlreadyExistException.class, () -> updateCustomerUseCase.updateCustomer(1L, customerRequest));
        assertTrue(e.getMessage().equalsIgnoreCase("Email "+ customerRequest.getEmail() + " already exist!"));
    }
}