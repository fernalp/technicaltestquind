package io.quind.technicaltesthexagonal.modules.account.domain.ports.in;

import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountRequest;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;

public interface CreateAccountUseCase {
    AccountResponse createAccount(AccountRequest accountRequest);

}
