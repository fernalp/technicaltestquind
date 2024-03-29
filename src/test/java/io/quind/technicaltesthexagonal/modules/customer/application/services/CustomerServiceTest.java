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
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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



    @Test
    void createCustomer() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void getCustomerById() {
    }

    @Test
    void getAllCustomer() {
    }

    @Test
    void updateCustomer() {
    }
}