package io.quind.technicaltesthexagonal.modules.account.domain.ports.in;

import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountRequest;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;

import java.util.Optional;

public interface CreateAccountUseCase {

    Optional<AccountResponse> createCustomer(AccountRequest accountRequest);

}
