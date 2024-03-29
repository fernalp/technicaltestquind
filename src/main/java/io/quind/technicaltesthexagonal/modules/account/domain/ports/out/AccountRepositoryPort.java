package io.quind.technicaltesthexagonal.modules.account.domain.ports.out;

import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepositoryPort {

    Account save(Account account);
    Optional<Account> findById(Long id);
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findAll();
    List<Account> findAllByCustomerById(Long id);
    boolean existsByAccountNumber(String accountNumber);
    Account update(Account account);
    boolean deleteById(Long id);
}
