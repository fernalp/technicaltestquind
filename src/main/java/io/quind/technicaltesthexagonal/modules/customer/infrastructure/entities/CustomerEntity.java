package io.quind.technicaltesthexagonal.modules.customer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
@Table(name = "tb_customers")
@Builder
public class CustomerEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    @Column(nullable = false, updatable = false)
    private Long customerId;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IdType idType;
    @Column(unique = true, nullable = false)
    private Integer idNumber;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column
    private Date birthdate;
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    @Column(
            updatable = false, nullable = false
    )
    private Date createdAt;
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private Date updatedAt;
}
