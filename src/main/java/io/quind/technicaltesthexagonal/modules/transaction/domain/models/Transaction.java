package io.quind.technicaltesthexagonal.modules.transaction.domain.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Transaction {
    private Long id;
    private BigDecimal amount;
    private TransactionType transactionType;
    private LocalDateTime date;
    private String originAccountNumber;
    private String destinationAccountNumber;
    @JsonManagedReference
    private Account originAccount;
    @JsonManagedReference
    private Account destinationAccount; // Only Transfers;
}
