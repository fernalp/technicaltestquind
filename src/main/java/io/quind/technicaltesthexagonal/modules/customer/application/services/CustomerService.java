package io.quind.technicaltesthexagonal.modules.customer.application.services;

import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.CreateCustomerUseCase;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.DeleteCustomerUseCase;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.RetrieveCustomerUseCase;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.UpdateCustomerUseCase;


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
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        return createCustomerUseCase.createCustomer(customerRequest);
    }

    @Override
    public void deleteCustomer(Long id) {
        deleteCustomerUseCase.deleteCustomer(id);
    }

    @Override
    public Optional<CustomerResponse> getCustomerById(Long id) {
        return retrieveCustomerUseCase.getCustomerById(id);
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        return retrieveCustomerUseCase.getAllCustomer();
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        return updateCustomerUseCase.updateCustomer(id, customerRequest);
    }


}
