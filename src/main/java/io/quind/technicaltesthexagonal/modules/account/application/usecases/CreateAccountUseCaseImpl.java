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
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;

public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;
    private final CustomerRepositoryPort customerRepositoryPort;

    public CreateAccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort, CustomerRepositoryPort customerRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
        this.customerRepositoryPort = customerRepositoryPort;
    }


    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {

        var utils = new UtilsAccount(accountRepositoryPort);

        Account account = AccountMapper.fromAccountRequest(accountRequest);

        // We assign the client
        account.setCustomer(customerRepositoryPort.findById(accountRequest.getCustomerId()).orElseThrow(
                () -> new RuntimeException("There is no client with the id "+ accountRequest.getCustomerId())
        ));

        // We generate and assign the account number
        account.setAccountNumber(utils.generateAccountNumber(account.getAccountType()));

        if (account.getAccountType().equals(AccountType.ACC_SAVINGS)) {
            account.setAccountStatus(AccountStatus.ACTIVE);
        }

        return AccountMapper.toAccountResponse(accountRepositoryPort.save(account));
    }


}
