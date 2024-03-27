package io.quind.technicaltesthexagonal.customer.domain.dtos;

import io.quind.technicaltesthexagonal.customer.domain.models.IdType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CustomerRequest {
    private String idType;
    private Integer idNumber;
    private String firstname;
    private String lastname;
    private String email;
    private Date birthdate;

}
