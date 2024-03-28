package io.quind.technicaltesthexagonal.modules.account.domain.dtos;

import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountStatus;
import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountType;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountResponse {
    private Long id;
    private AccountType accountType;
    private String accountNumber;
    private AccountStatus accountStatus;
    private BigDecimal balance;
    private boolean gmfExempt;
    private Customer customer;
}
