package io.quind.technicaltesthexagonal.modules.transaction.domain.dtos;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionRequest {
    @Positive
    @DecimalMin(value = "100.00", message = "The min value for a transaction is 100.00")
    private BigDecimal amount;
    @Pattern(regexp = "^(CONSIGNMENT|WITHDRAWAL|TRANSFER)$", message = "The Account Type must be CONSIGNMENT, WITHDRAWAL OR TRANSFER")
    private String transactionType;
    @NotNull(message = "The Origin Account cannot be null and must be 10 number!")
    @Size(min = 10, max = 10, message = "The Origin Account Number must be 10 number!")
    private String originAccountNumber;
    @Null(message = "The Destination Account cannot be null in consignment and withdrawal, otherwise must be 10 number")
    @Size(min = 10, max = 10, message = "The Origin Account Number must be 10 number!")
    private String destinationAccountNumber;

}
