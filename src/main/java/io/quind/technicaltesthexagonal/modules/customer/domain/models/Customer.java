package io.quind.technicaltesthexagonal.modules.customer.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class Customer {
    private Long id;
    private IdType idType;
    private String idNumber;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    @JsonBackReference
    private List<Account> accounts;

}
