package io.quind.technicaltesthexagonal.modules.account.domain.dtos;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountRequest {

    @Pattern(regexp = "^(ACC_CHECKING|ACC_SAVINGS)$", message = "The Account Type must be ACC_CHECKING OR ACC_SAVINGS")
    private String accountType;
    @PositiveOrZero(message = "The Balance Must Be Positive")
    @DecimalMin(value = "0.00")
    private BigDecimal balance;
    private boolean gmfExempt;
    @NotNull(message = "The Customer id cannot be null!")
    @Min(value = 0)
    private Long customerId;

}
