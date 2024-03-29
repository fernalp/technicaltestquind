package io.quind.technicaltesthexagonal.modules.account.domain.ports.in;

import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountRequest;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;
public interface UpdateAccountUseCase {

    AccountResponse update(Long id, AccountRequest accountRequest);
    AccountResponse update(String accountNumber, AccountRequest accountRequest);
}
