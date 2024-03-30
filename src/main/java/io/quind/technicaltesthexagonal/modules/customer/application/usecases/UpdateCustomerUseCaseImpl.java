package io.quind.technicaltesthexagonal.modules.customer.application.usecases;

import io.quind.technicaltesthexagonal.core.utils.UtilsCustomer;
import io.quind.technicaltesthexagonal.modules.customer.application.exceptions.AlreadyExistException;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.mappers.CustomerMapper;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.IdType;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.UpdateCustomerUseCase;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;

import java.util.Optional;

public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public UpdateCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        var utils = new UtilsCustomer(customerRepositoryPort);
        Optional<Customer> customer = customerRepositoryPort.findById(id);
        if (customer.isPresent()) {
            Customer customerUpdated = customer.get();
            customerUpdated.setFirstname(customerRequest.getFirstname());
            customerUpdated.setLastname(customerRequest.getLastname());
            customerUpdated.setIdType(IdType.valueOf(customerRequest.getIdType()));
            if (!customerUpdated.getIdNumber().equals(customerRequest.getIdNumber())) {
                utils.validateIdNumber(customerRequest.getIdNumber());
            }
            if (!customerUpdated.getEmail().equalsIgnoreCase(customerRequest.getEmail())) {
                utils.validateEmail(customerRequest.getEmail());
            }
            utils.validateAgeByDate(customerRequest.getDateOfBirth());
            customerUpdated.setIdNumber(customerRequest.getIdNumber());
            customerUpdated.setEmail(customerRequest.getEmail());
            customerUpdated.setDateOfBirth(customerRequest.getDateOfBirth());
            return CustomerMapper.toCustomerResponse(customerRepositoryPort.save(customerUpdated));
        }
        throw new IllegalArgumentException("There is no client with the id" + id);
    }
}
