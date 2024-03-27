package io.quind.technicaltesthexagonal.customer.domain.ports.in;

public interface DeleteCustomerUseCase {

    boolean deleteCustomer(Long id);
}
