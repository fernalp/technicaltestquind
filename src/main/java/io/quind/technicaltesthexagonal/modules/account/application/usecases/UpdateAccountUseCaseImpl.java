package io.quind.technicaltesthexagonal.modules.account.application.usecases;

import io.quind.technicaltesthexagonal.modules.account.application.utils.UtilsAccount;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountRequest;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;
import io.quind.technicaltesthexagonal.modules.account.domain.mappers.AccountMapper;
import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.in.UpdateAccountUseCase;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;

import java.util.Optional;

public class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    public UpdateAccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }


    @Override
    public AccountResponse update(Long id, AccountRequest accountRequest) {
        Optional<Account> accountOptional = accountRepositoryPort.findById(id);
        return AccountMapper.toAccountResponse(accountRepositoryPort.save(updateField(accountOptional, accountRequest)));
    }

    @Override
    public AccountResponse update(String accountNumber, AccountRequest accountRequest) {
        Optional<Account> accountOptional = accountRepositoryPort.findByAccountNumber(accountNumber);

        return AccountMapper.toAccountResponse(accountRepositoryPort.save(updateField(accountOptional,accountRequest)));
    }

    private Account updateField(Optional<Account> accountOptional, AccountRequest newAccount){
        if (accountOptional.isEmpty()) {
            throw new IllegalArgumentException("There is no account with that id");
        }
        Account oldAccount = accountOptional.get();
        UtilsAccount.isBalanceGreaterThanZero(newAccount.getBalance());
        oldAccount.setAccountStatus(newAccount.getAccountStatus());
        oldAccount.setBalance(newAccount.getBalance());
        oldAccount.setGmfExempt(newAccount.isGmfExempt());
        return accountRepositoryPort.update(oldAccount);
    }
}
