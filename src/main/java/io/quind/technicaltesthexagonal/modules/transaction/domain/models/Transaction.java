package io.quind.technicaltesthexagonal.modules.transaction.domain.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private BigDecimal amount;
    private TransactionType transactionType;
    private LocalDateTime date;
    @JsonManagedReference
    private Account sourceAccount;
    @JsonManagedReference
    private Account destinationAccount; // Solo para transferencias;
}
