package io.quind.technicaltesthexagonal.modules.account.domain.ports.in;

import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.UpdateAccountStatusRequest;

import java.math.BigDecimal;

public interface UpdateAccountUseCase {

    AccountResponse updateAccountStatus(String accountNumber, UpdateAccountStatusRequest updateAccountStatusRequest);
    AccountResponse updateAccountBalance(String accountNumber, BigDecimal newBalance);
    void cancelAccount(String accountNumber);
}
