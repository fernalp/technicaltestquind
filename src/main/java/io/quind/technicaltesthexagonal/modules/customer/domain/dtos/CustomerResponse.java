package io.quind.technicaltesthexagonal.modules.customer.domain.dtos;

import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.IdType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CustomerResponse{
    private Long id;
    private IdType idType;
    private String idNumber;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDate dateOfBirth;
}


