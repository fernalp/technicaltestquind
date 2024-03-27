package io.quind.technicaltesthexagonal.modules.customer.domain.ports.in;

public interface DeleteCustomerUseCase {

    boolean deleteCustomer(Long id);
}
