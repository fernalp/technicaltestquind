package io.quind.technicaltesthexagonal.modules.transaction.application.usecases;

import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;
import io.quind.technicaltesthexagonal.modules.transaction.domain.dtos.TransactionRequest;
import io.quind.technicaltesthexagonal.modules.transaction.domain.dtos.TransactionResponse;
import io.quind.technicaltesthexagonal.modules.transaction.domain.mappers.TransactionMapper;
import io.quind.technicaltesthexagonal.modules.transaction.domain.models.Transaction;
import io.quind.technicaltesthexagonal.modules.transaction.domain.models.TransactionType;
import io.quind.technicaltesthexagonal.modules.transaction.domain.ports.in.CreateTransactionUseCase;
import io.quind.technicaltesthexagonal.modules.transaction.domain.ports.out.TransactionRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final AccountRepositoryPort accountRepositoryPort;

    public CreateTransactionUseCaseImpl(TransactionRepositoryPort transactionRepositoryPort, AccountRepositoryPort accountRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Transactional
    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {

        Transaction transaction = TransactionMapper.fromTransactionRequest(transactionRequest);
        Account originAccount = getAccount(transactionRequest.getOriginAccountId());

        if (transactionRequest.getTransactionType().equals(TransactionType.CONSIGNMENT)){
            BigDecimal newBalance = originAccount.getBalance().add(transactionRequest.getAmount());
            originAccount.setBalance(newBalance);
        }
        else if (transactionRequest.getTransactionType().equals(TransactionType.WITHDRAWAL)){
            BigDecimal newBalance = originAccount.getBalance().subtract(transactionRequest.getAmount());
            if (newBalance.compareTo(BigDecimal.ZERO) < 0){
                throw new IllegalArgumentException("You do not have sufficient funds for this transaction");
            }
            originAccount.setBalance(newBalance);
        }

        else if (transactionRequest.getTransactionType().equals(TransactionType.TRANSFER)){
            Account destinationAccount = getAccount(transactionRequest.getDestinationAccountId());
            BigDecimal newBalanceOrigin = originAccount.getBalance().subtract(transactionRequest.getAmount());
            BigDecimal newBalanceDestination = destinationAccount.getBalance().add(transactionRequest.getAmount());
            if (newBalanceOrigin.compareTo(BigDecimal.ZERO) < 0){
                throw new IllegalArgumentException("You do not have sufficient funds for this transaction");
            }
            originAccount.setBalance(newBalanceOrigin);
            destinationAccount.setBalance(newBalanceDestination);
            transaction.setDestinationAccount(accountRepositoryPort.save(destinationAccount));
        }
        transaction.setOriginAccount(accountRepositoryPort.save(originAccount));
        return TransactionMapper.toTransactionResponse(transactionRepositoryPort.createTransaction(transaction));
    }

    private Account getAccount(String accountNumber){
        Optional<Account> accountOptional = accountRepositoryPort.findByAccountNumber(accountNumber);
        if (accountOptional.isEmpty()){
            throw new IllegalArgumentException("This account "+ accountNumber + " not exist!");
        }
        return Account.builder()
                .id(accountOptional.get().getId())
                .accountType(accountOptional.get().getAccountType())
                .accountNumber(accountOptional.get().getAccountNumber())
                .accountStatus(accountOptional.get().getAccountStatus())
                .balance(accountOptional.get().getBalance())
                .gmfExempt(accountOptional.get().isGmfExempt())
                .customerId(accountOptional.get().getCustomerId())
                .customer(accountOptional.get().getCustomer())
                .build();
    }

}
