package io.quind.technicaltesthexagonal.modules.customer.domain.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class Customer {
    private Long id;
    private IdType idType;
    private String idNumber;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDate birthdate;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
