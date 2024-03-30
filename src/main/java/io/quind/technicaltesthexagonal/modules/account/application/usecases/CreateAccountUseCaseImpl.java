package io.quind.technicaltesthexagonal.modules.account.application.usecases;

import io.quind.technicaltesthexagonal.core.utils.UtilsAccount;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountRequest;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;
import io.quind.technicaltesthexagonal.modules.account.domain.mappers.AccountMapper;
import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;
import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountStatus;
import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountType;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.in.CreateAccountUseCase;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;

import java.util.Optional;

public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;
    private final CustomerRepositoryPort customerRepositoryPort;

    public CreateAccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort, CustomerRepositoryPort customerRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
        this.customerRepositoryPort = customerRepositoryPort;
    }


    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {

        Account account = AccountMapper.fromAccountRequest(accountRequest);
        Optional<Customer> customer = customerRepositoryPort.findById(accountRequest.getCustomerId());

        String numberAccount = UtilsAccount.generateAccountNumber(account.getAccountType());
        while (accountRepositoryPort.existsByAccountNumber(numberAccount)){
            numberAccount = UtilsAccount.generateAccountNumber(account.getAccountType());
        }
        account.setAccountNumber(numberAccount);

        if (account.getAccountType().equals(AccountType.ACC_SAVINGS)){
            account.setAccountStatus(AccountStatus.ACTIVE);
        }else{
            account.setAccountStatus(AccountStatus.INACTIVE);
        }
        UtilsAccount.isBalanceGreaterThanZero(account.getBalance());
        customer.ifPresent(account::setCustomer);
        try{
            return AccountMapper.toAccountResponse(accountRepositoryPort.save(account));
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }


}
