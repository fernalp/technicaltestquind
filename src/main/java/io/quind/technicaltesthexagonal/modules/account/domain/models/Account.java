package io.quind.technicaltesthexagonal.modules.account.domain.models;

import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class Account {
    private Long id;
    private AccountType accountType;
    private String accountNumber;
    private AccountStatus accountStatus;
    private BigDecimal balance;
    private boolean gmfExempt;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Customer customer;
}
