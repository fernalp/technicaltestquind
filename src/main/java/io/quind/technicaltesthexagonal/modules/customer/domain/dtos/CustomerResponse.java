package io.quind.technicaltesthexagonal.modules.customer.domain.dtos;

import io.quind.technicaltesthexagonal.modules.customer.domain.models.IdType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

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


