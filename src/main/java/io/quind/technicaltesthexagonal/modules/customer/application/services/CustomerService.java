package io.quind.technicaltesthexagonal.modules.customer.application.services;

import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.CreateCustomerUseCase;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.DeleteCustomerUseCase;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.RetrieveCustomerUseCase;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.UpdateCustomerUseCase;


import java.time.LocalDate;
import java.time.Period;
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
    public Optional<CustomerResponse> createCustomer(CustomerRequest customerRequest) {
        int age = calculateAge(customerRequest.getDateOfBirth(), LocalDate.now());
        if (age >= 18){
            return createCustomerUseCase.createCustomer(customerRequest);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteCustomer(Long id) {
        return deleteCustomerUseCase.deleteCustomer(id);
    }

    @Override
    public Optional<CustomerResponse> getCustomer(Long id) {
        return retrieveCustomerUseCase.getCustomer(id);
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        return retrieveCustomerUseCase.getAllCustomer();
    }

    @Override
    public Optional<CustomerResponse> updateCustomer(Long id, CustomerRequest customerRequest) {
        int age = calculateAge(customerRequest.getDateOfBirth(), LocalDate.now());
        if (age >= 18){
            return updateCustomerUseCase.updateCustomer(id, customerRequest);
        }
        return Optional.empty();
    }

    private static int calculateAge(LocalDate birthdayDate, LocalDate currentDate){
        Period period = Period.between(birthdayDate, currentDate);
        return period.getYears();
    }
}
