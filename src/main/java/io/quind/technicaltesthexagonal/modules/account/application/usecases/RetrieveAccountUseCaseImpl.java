package io.quind.technicaltesthexagonal.modules.account.application.usecases;

import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;
import io.quind.technicaltesthexagonal.modules.account.domain.mappers.AccountMapper;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.in.RetrieveAccountUseCase;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RetrieveAccountUseCaseImpl implements RetrieveAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    public RetrieveAccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public Optional<AccountResponse> findByAccountNumber(String accountNumber) {
        return accountRepositoryPort.findByAccountNumber(accountNumber)
                .map(AccountMapper::toAccountResponse);
    }

    @Override
    public List<AccountResponse> findAll() {
        return accountRepositoryPort.findAll()
                .stream().map(AccountMapper::toAccountResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountResponse> findAllByCustomerById(Long id) {
        return accountRepositoryPort.findAllByCustomerById(id)
                .stream().map(AccountMapper::toAccountResponse)
                .collect(Collectors.toList());
    }
}
