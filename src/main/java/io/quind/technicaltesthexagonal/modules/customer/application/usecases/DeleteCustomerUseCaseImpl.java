package io.quind.technicaltesthexagonal.modules.customer.application.usecases;

import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.in.DeleteCustomerUseCase;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;

import java.util.List;
import java.util.Optional;

public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;
    private final AccountRepositoryPort accountRepositoryPort;

    public DeleteCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort, AccountRepositoryPort accountRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> customerOptional = customerRepositoryPort.findById(id);
        if (customerOptional.isEmpty()){
            throw new RuntimeException("There is no client with that id");
        }
        List<Account> accounts = accountRepositoryPort.findAllByCustomerById(id);
        if (!accounts.isEmpty()){
            throw new RuntimeException("The id " + id + " still has accounts, you must cancel the accounts.");
        }
        customerRepositoryPort.deleteById(id);
    }
}
