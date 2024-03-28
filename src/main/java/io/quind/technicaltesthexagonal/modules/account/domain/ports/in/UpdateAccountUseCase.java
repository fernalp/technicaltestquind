package io.quind.technicaltesthexagonal.modules.account.domain.ports.in;

import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;

import java.util.Optional;

public interface UpdateAccountUseCase {

    Optional<Account> update(Long id, Account account);
}
