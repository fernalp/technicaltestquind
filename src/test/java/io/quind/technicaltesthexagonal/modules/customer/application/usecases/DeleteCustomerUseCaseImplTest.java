package io.quind.technicaltesthexagonal.modules.customer.application.usecases;

import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteCustomerUseCaseImplTest {
    @Mock
    private CustomerRepositoryPort customerRepositoryPort;
    @Mock
    private AccountRepositoryPort accountRepositoryPort;
    @InjectMocks
    private DeleteCustomerUseCaseImpl deleteCustomerUseCase;

    @Test
    void useCase_DeleteCustomerWithIdInvalid_ReturnThrow() {
        when(customerRepositoryPort.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Exception e = assertThrows(RuntimeException.class, ()-> deleteCustomerUseCase.deleteCustomer(1L));
        assertTrue(e.getMessage().equalsIgnoreCase("There is no client with that id"));
    }

    @Test
    void useCase_DeleteCustomerWithAccount_ReturnThrow(){
        Long id = 1L;
        when(customerRepositoryPort.findById(Mockito.anyLong())).thenReturn(Optional.of(Customer.builder().build()));
        when(accountRepositoryPort.findAllByCustomerById(id)).thenReturn(List.of(Account.builder().build(),  Account.builder().build()));
        Exception e = assertThrows(RuntimeException.class, () -> deleteCustomerUseCase.deleteCustomer(id));
        assertTrue(e.getMessage().equalsIgnoreCase("The id " + id + " still has accounts, you must cancel the accounts."));
    }

    @Test
    void useCase_DeleteCustomer_InvokeDeleteById(){
        when(customerRepositoryPort.findById(Mockito.anyLong())).thenReturn(Optional.of(Customer.builder().build()));
        when(accountRepositoryPort.findAllByCustomerById(Mockito.anyLong())).thenReturn(List.of());
        deleteCustomerUseCase.deleteCustomer(1L);
        verify(customerRepositoryPort, times(1)).deleteById(1L);
        }
}