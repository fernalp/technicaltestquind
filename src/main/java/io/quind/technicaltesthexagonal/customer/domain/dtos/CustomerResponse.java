package io.quind.technicaltesthexagonal.customer.domain.dtos;

import io.quind.technicaltesthexagonal.customer.domain.models.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class CustomerResponse extends CustomerRequest{
    private Long customerId;
}
