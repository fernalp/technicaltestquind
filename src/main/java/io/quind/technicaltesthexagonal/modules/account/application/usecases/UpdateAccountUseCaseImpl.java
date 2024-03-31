package io.quind.technicaltesthexagonal.modules.account.application.usecases;

import io.quind.technicaltesthexagonal.core.utils.UtilsAccount;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.UpdateAccountStatusRequest;
import io.quind.technicaltesthexagonal.modules.account.domain.mappers.AccountMapper;
import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;
import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountStatus;
import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountType;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.in.UpdateAccountUseCase;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;

import java.math.BigDecimal;

public class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    public UpdateAccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public AccountResponse updateAccountStatus(String accountNumber, UpdateAccountStatusRequest updateAccountStatusRequest) {
        Account account = getAccountByAccountNumber(accountNumber);
        account.setAccountStatus(AccountStatus.valueOf(updateAccountStatusRequest.getAccountStatus()));
        return AccountMapper.toAccountResponse(accountRepositoryPort.save(account));
    }

    @Override
    public AccountResponse updateAccountBalance(String accountNumber, BigDecimal newBalance) {
        var utils = new UtilsAccount(accountRepositoryPort);
        Account account = getAccountByAccountNumber(accountNumber);
        if (account.getAccountType().equals(AccountType.ACC_SAVINGS) && !utils.isBalanceGreaterThanZero(newBalance)){
            throw new RuntimeException("Your Balance is Insufficient!");
        }
        account.setBalance(newBalance);
        return AccountMapper.toAccountResponse(accountRepositoryPort.save(account));
    }

    @Override
    public void cancelAccount(String accountNumber) {
        Account account = getAccountByAccountNumber(accountNumber);
        if (account.getBalance().compareTo(BigDecimal.ZERO) > 0){
            throw new RuntimeException("This account must be at 0 to be eliminated");
        }
        account.setAccountStatus(AccountStatus.CANCELED);
        accountRepositoryPort.save(account);
    }

    private Account getAccountByAccountNumber(String accountNumber){
        return accountRepositoryPort.findByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException("This account "+ accountNumber + " is invalid!"));
    }

}
