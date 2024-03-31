package io.quind.technicaltesthexagonal.modules.customer.application.usecases;

import io.quind.technicaltesthexagonal.core.utils.UtilsCustomer;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.mappers.CustomerMapper;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.CreateCustomerUseCase;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;

public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public CreateCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        var utils = new UtilsCustomer(customerRepositoryPort);
        utils.validateAgeByDate(customerRequest.getDateOfBirth());
        utils.validateIdNumber(customerRequest.getIdNumber());
        utils.validateEmail(customerRequest.getEmail());
        Customer customer = CustomerMapper.fromCustomerRequest(customerRequest);
        return CustomerMapper.toCustomerResponse(customerRepositoryPort.save(customer));
    }



}
