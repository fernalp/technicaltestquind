package io.quind.technicaltesthexagonal.modules.transaction.domain.dtos;

import io.quind.technicaltesthexagonal.modules.transaction.domain.models.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionRequest {
    private BigDecimal amount;
    private TransactionType transactionType;
    private LocalDateTime date;
    private String originAccountId;
    private String destinationAccountId;
}
