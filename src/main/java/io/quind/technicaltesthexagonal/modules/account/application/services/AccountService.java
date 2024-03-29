package io.quind.technicaltesthexagonal.modules.account.application.services;

import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountRequest;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.in.CreateAccountUseCase;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.in.DeleteAccountUseCase;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.in.RetrieveAccountUseCase;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.in.UpdateAccountUseCase;

import java.util.List;
import java.util.Optional;


public class AccountService implements CreateAccountUseCase, RetrieveAccountUseCase, UpdateAccountUseCase, DeleteAccountUseCase{

    private final CreateAccountUseCase createAccountUseCase;
    private final RetrieveAccountUseCase retrieveAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;

    public AccountService(CreateAccountUseCase createAccountUseCase, RetrieveAccountUseCase retrieveAccountUseCase, UpdateAccountUseCase updateAccountUseCase, DeleteAccountUseCase deleteAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.retrieveAccountUseCase = retrieveAccountUseCase;
        this.updateAccountUseCase = updateAccountUseCase;
        this.deleteAccountUseCase = deleteAccountUseCase;
    }

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {
        return createAccountUseCase.createAccount(accountRequest);
    }

    @Override
    public boolean deleteAccount(Long id) {
        return deleteAccountUseCase.deleteAccount(id);
    }

    @Override
    public Optional<AccountResponse> findById(Long id) {
        return retrieveAccountUseCase.findById(id);
    }

    @Override
    public Optional<AccountResponse> findByAccountNumber(String accountNumber) {
        return retrieveAccountUseCase.findByAccountNumber(accountNumber);
    }

    @Override
    public List<AccountResponse> findAll() {
        return retrieveAccountUseCase.findAll();
    }

    @Override
    public List<AccountResponse> findAllByCustomerById(Long id) {
        return retrieveAccountUseCase.findAllByCustomerById(id);
    }

    @Override
    public AccountResponse update(Long id, AccountRequest account) {
        return updateAccountUseCase.update(id, account);
    }

    @Override
    public AccountResponse update(String accountNumber, AccountRequest account) {
        return updateAccountUseCase.update(accountNumber, account);
    }

}
