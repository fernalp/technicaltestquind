package io.quind.technicaltesthexagonal.modules.account.application.usecases;

import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountRequest;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.in.CreateAccountUseCase;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;

import java.util.Optional;

public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    public CreateAccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }


    @Override
    public Optional<AccountResponse> createCustomer(AccountRequest accountRequest) {
        return Optional.empty();
    }
}
