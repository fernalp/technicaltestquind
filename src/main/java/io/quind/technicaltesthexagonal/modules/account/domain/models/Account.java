package io.quind.technicaltesthexagonal.modules.account.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.transaction.domain.models.Transaction;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDateTime updatedAt;
    private Long customerId;
    @JsonManagedReference
    private Customer customer;
    @JsonBackReference
    private List<Transaction> outgoingTransactions;
    @JsonBackReference
    private List<Transaction> incomingTransactions;
}
