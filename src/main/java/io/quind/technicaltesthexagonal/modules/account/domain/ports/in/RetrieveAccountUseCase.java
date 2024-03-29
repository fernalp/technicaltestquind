package io.quind.technicaltesthexagonal.modules.account.domain.ports.in;

import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;

import java.util.List;
import java.util.Optional;

public interface RetrieveAccountUseCase {

    Optional<AccountResponse> findById(Long id);
    Optional<AccountResponse> findByAccountNumber(String accountNumber);
    List<AccountResponse> findAll();
    List<AccountResponse> findAllByCustomerById(Long id);

}
