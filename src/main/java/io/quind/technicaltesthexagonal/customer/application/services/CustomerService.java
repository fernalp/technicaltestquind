package io.quind.technicaltesthexagonal.customer.application.services;

import io.quind.technicaltesthexagonal.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.customer.domain.ports.in.CreateCustomerUseCase;
import io.quind.technicaltesthexagonal.customer.domain.ports.in.DeleteCustomerUseCase;
import io.quind.technicaltesthexagonal.customer.domain.ports.in.RetrieveCustomerUseCase;
import io.quind.technicaltesthexagonal.customer.domain.ports.in.UpdateCustomerUseCase;

import java.util.List;
import java.util.Optional;

public class CustomerService implements CreateCustomerUseCase, RetrieveCustomerUseCase, UpdateCustomerUseCase, DeleteCustomerUseCase {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final RetrieveCustomerUseCase retrieveCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    public CustomerService(CreateCustomerUseCase createCustomerUseCase, RetrieveCustomerUseCase retrieveCustomerUseCase, UpdateCustomerUseCase updateCustomerUseCase, DeleteCustomerUseCase deleteCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.retrieveCustomerUseCase = retrieveCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return createCustomerUseCase.createCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(Long id) {
        return deleteCustomerUseCase.deleteCustomer(id);
    }

    @Override
    public Optional<Customer> getCustomer(Long id) {
        return retrieveCustomerUseCase.getCustomer(id);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return retrieveCustomerUseCase.getAllCustomer();
    }

    @Override
    public Optional<Customer> updateCustomer(Long id, Customer customer) {
        return updateCustomerUseCase.updateCustomer(id, customer);
    }
}
