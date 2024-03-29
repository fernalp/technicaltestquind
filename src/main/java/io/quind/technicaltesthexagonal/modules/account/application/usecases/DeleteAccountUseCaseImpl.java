package io.quind.technicaltesthexagonal.modules.account.application.usecases;

import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;
import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountStatus;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.in.DeleteAccountUseCase;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;

import java.math.BigDecimal;
import java.util.Optional;


public class DeleteAccountUseCaseImpl implements DeleteAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    public DeleteAccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }


    @Override
    public boolean deleteAccount(Long id) {

        Optional<Account> account = accountRepositoryPort.findById(id);

        if (account.isEmpty()) {
            throw new IllegalArgumentException("This account no exist");
        }else {
            Account acc = account.get();
            if (acc.getBalance().compareTo(BigDecimal.ZERO) != 0) {
                throw new IllegalArgumentException("The account must be without funds to be canceled");
            }
            acc.setAccountStatus(AccountStatus.CANCELED);
            accountRepositoryPort.save(acc);
            return true;
        }
    }
}
