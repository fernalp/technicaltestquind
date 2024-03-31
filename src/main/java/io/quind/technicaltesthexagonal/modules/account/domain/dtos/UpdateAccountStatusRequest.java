package io.quind.technicaltesthexagonal.modules.account.domain.dtos;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateAccountStatusRequest {
    @Pattern(regexp = "^(ACTIVE|INACTIVE)$", message = "The Account Status must be ACTIVE OR INACTIVE")
    private String accountStatus;
}
