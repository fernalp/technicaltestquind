package io.quind.technicaltesthexagonal.modules.account.domain.dtos;

import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountStatus;
import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountRequest {

    private AccountType accountType;
    private AccountStatus accountStatus;
    private BigDecimal balance;
    private boolean gmfExempt;
    private Long customerId;

}
