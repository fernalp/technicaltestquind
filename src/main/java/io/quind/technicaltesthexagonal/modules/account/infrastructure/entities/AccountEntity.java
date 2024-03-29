package io.quind.technicaltesthexagonal.modules.account.infrastructure.entities;

import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountStatus;
import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountType;
import io.quind.technicaltesthexagonal.modules.customer.infrastructure.entities.CustomerEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, updatable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(unique = true, nullable = false, length = 10, updatable = false)
    private String accountNumber;
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @Column(nullable = false, scale = 2)
    private BigDecimal balance;
    @Column(nullable = false)
    private boolean gmfExempt;
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;

}
