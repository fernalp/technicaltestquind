package io.quind.technicaltesthexagonal.modules.customer.domain.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
public class Customer {
    private Long customerId;
    private String idType;
    private Integer idNumber;
    private String firstname;
    private String lastname;
    private String email;
    private Date birthdate;
    private Date createdAt;
    private Date updatedAt;

    public Customer(Long customerId, String idType, Integer idNumber, String firstname, String lastname, String email, Date birthdate, Date createdAt, Date updatedAt) {
        this.customerId = customerId;
        this.idType = idType;
        this.idNumber = idNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.birthdate = birthdate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
