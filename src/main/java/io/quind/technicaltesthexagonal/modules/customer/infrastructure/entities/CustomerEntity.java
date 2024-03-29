package io.quind.technicaltesthexagonal.modules.customer.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quind.technicaltesthexagonal.modules.account.infrastructure.entities.AccountEntity;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.IdType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_customers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private Long customerId;
    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private IdType idType;
    @Column(unique = true, nullable = false)
    @Length(min = 6, max = 15)
    private String idNumber;
    @Column(nullable = false)
    @Length(min = 2, message = "Firstname is short")
    private String firstname;
    @Column(nullable = false)
    @Length(min = 2, message = "Firstname is short")
    private String lastname;
    @Column(nullable = false, unique = true)
    @Pattern(
            regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "Email is not valid"
    )
    private String email;
    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate birthdate;
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    @Column(
            updatable = false, nullable = false
    )
    private LocalDate createdAt;
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    @Column(
            nullable = false
    )
    private LocalDate updatedAt;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "customer"
    )
    private List<AccountEntity> accounts;

}
