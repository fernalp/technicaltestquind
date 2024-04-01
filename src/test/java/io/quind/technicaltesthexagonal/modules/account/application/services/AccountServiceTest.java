package io.quind.technicaltesthexagonal.modules.account.application.services;

import io.quind.technicaltesthexagonal.modules.account.application.usecases.CreateAccountUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.account.application.usecases.DeleteAccountUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.account.application.usecases.RetrieveAccountUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.account.application.usecases.UpdateAccountUseCaseImpl;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountRequest;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;
import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountType;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    private AccountRequest accountRequest;

    @Mock
    private CreateAccountUseCaseImpl createAccountUseCase;
    @Mock
    private RetrieveAccountUseCaseImpl retrieveAccountUseCase;
    @Mock
    private UpdateAccountUseCaseImpl updateAccountUseCase;
    @Mock
    private DeleteAccountUseCaseImpl deleteAccountUseCase;
    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {

    }

    @DisplayName("Test create an Account")
    @Test
    void createAccount() {

    }

    @Test
    void deleteAccount() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByAccountNumber() {
    }

    @DisplayName("Get All Accounts")
    @Test
    void canFindAll() {

    }

    @Test
    void findAllByCustomerById() {
    }

    @Test
    void update() {
    }

    @Test
    void testUpdate() {
    }
}