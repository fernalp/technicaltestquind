package io.quind.technicaltesthexagonal.modules.account.infrastructure.repositories;

import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;
import io.quind.technicaltesthexagonal.modules.account.infrastructure.entities.AccountEntity;
import io.quind.technicaltesthexagonal.modules.account.infrastructure.mappers.AccountEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaAccountRepositoryAdapter implements AccountRepositoryPort {

    private final JpaAccountRepository jpaAccountRepository;

    public JpaAccountRepositoryAdapter(JpaAccountRepository jpaAccountRepository) {
        this.jpaAccountRepository = jpaAccountRepository;
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = AccountEntityMapper.fromAccount(account);
        return AccountEntityMapper.toAccount(jpaAccountRepository.save(accountEntity));
    }

    @Override
    public Optional<Account> findById(Long id) {
        return jpaAccountRepository.findById(id).map(AccountEntityMapper::toAccount);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return jpaAccountRepository.findByAccountNumber(accountNumber).map(AccountEntityMapper::toAccount);
    }

    @Override
    public List<Account> findAll() {
        return jpaAccountRepository.findAll().stream().map(AccountEntityMapper::toAccount).collect(Collectors.toList());
    }

    @Override
    public List<Account> findAllByCustomerById(Long id) {
        return jpaAccountRepository.findByCustomer_CustomerId(id).stream().map(AccountEntityMapper::toAccount).collect(Collectors.toList());
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        return jpaAccountRepository.existsByAccountNumber(accountNumber);
    }

    @Override
    public Account update(Account account) {
        AccountEntity accountEntity = AccountEntityMapper.fromAccount(account);
        return AccountEntityMapper.toAccount(jpaAccountRepository.save(accountEntity));
    }

    @Override
    public boolean deleteById(Long id) {
        return jpaAccountRepository.existsById(id);
    }
}
