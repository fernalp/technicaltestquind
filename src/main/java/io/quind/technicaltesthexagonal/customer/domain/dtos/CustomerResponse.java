package io.quind.technicaltesthexagonal.customer.domain.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class CustomerResponse extends CustomerRequest{
    private Long customerId;
}
