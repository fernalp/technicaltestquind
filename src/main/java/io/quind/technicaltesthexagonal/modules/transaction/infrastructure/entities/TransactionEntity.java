package io.quind.technicaltesthexagonal.modules.transaction.infrastructure.entities;

import io.quind.technicaltesthexagonal.modules.account.infrastructure.entities.AccountEntity;
import io.quind.technicaltesthexagonal.modules.transaction.domain.models.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(
            nullable = false, updatable = false, scale = 2
    )
    private BigDecimal amount;
    @Column(nullable = false, updatable = false)
    private TransactionType transactionType;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp()
    @Column(nullable = false, updatable = false)
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "originAccountId", updatable = false)
    private AccountEntity originAccount;
    @ManyToOne
    @JoinColumn(name = "destinationAccountId", nullable = true, updatable = false)
    private AccountEntity destinationAccount;
}
