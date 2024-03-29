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
    private String originAccountId;
    private String destinationAccountId;
    @JsonManagedReference
    private Account originAccount;
    @JsonManagedReference
    private Account destinationAccount; // Solo para transferencias;
}
