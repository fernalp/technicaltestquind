package io.quind.technicaltesthexagonal.modules.account.domain.ports.in;

import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;

import java.util.List;
import java.util.Optional;

public interface RetrieveAccountUseCase {

    Optional<Account> findById(Long id);
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findAll();
    List<Account> findAllByCustomerByIdNumber(String idNumber);

}
