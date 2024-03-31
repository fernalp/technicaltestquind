package io.quind.technicaltesthexagonal.modules.account.application.usecases;

import io.quind.technicaltesthexagonal.core.utils.UtilsAccount;
import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;
import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountStatus;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.in.DeleteAccountUseCase;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;

public class DeleteAccountUseCaseImpl implements DeleteAccountUseCase {
    private final AccountRepositoryPort accountRepositoryPort;
    public DeleteAccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public void deleteAccount(String accountNumber) {

        var utils = new UtilsAccount(accountRepositoryPort);

        Account account = accountRepositoryPort.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("This account "+ accountNumber + " is invalid!"));

        if (utils.isBalanceGreaterThanZero(account.getBalance()))
            throw new RuntimeException("This account must be at 0 to be eliminated");

        account.setAccountStatus(AccountStatus.CANCELED);
        accountRepositoryPort.save(account);

    }
}
