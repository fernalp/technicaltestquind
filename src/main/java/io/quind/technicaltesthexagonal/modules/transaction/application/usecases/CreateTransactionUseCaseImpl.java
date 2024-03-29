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

        TransactionType transactionType = transactionRequest.getTransactionType();
        Transaction transaction = TransactionMapper.fromTransactionRequest(transactionRequest);
        Account originAccount = accountRepositoryPort.findByAccountNumber(transactionRequest.getOriginAccountId()).orElseThrow();

        if (transactionType.equals(TransactionType.CONSIGNMENT)){
            BigDecimal newBalance = originAccount.getBalance().add(transactionRequest.getAmount());
            originAccount.setBalance(newBalance);
        }
        if (transactionType.equals(TransactionType.WITHDRAWAL)){
            BigDecimal newBalance = originAccount.getBalance().subtract(transactionRequest.getAmount());
            if (newBalance.compareTo(BigDecimal.ZERO) < 0){
                throw new IllegalArgumentException("You do not have sufficient funds for this transaction");
            }
            originAccount.setBalance(newBalance);
        }

        if (transactionRequest.getTransactionType().equals(TransactionType.TRANSFER)){
            Account destinationAccount = accountRepositoryPort.findByAccountNumber(transactionRequest.getDestinationAccountId()).orElseThrow();
            BigDecimal newBalanceOrigin = originAccount.getBalance().subtract(transactionRequest.getAmount());
            BigDecimal newBalanceDestination = destinationAccount.getBalance().add(transactionRequest.getAmount());
            if (newBalanceOrigin.compareTo(BigDecimal.ZERO) < 0){
                throw new IllegalArgumentException("You do not have sufficient funds for this transaction");
            }
            originAccount.setBalance(newBalanceOrigin);
            destinationAccount.setBalance(newBalanceDestination);
            accountRepositoryPort.save(destinationAccount);
            transaction.setDestinationAccount(destinationAccount);
        }

        accountRepositoryPort.save(originAccount);
        transaction.setOriginAccount(originAccount);
        return TransactionMapper.toTransactionResponse(transactionRepositoryPort.createTransaction(transaction));
    }

}
