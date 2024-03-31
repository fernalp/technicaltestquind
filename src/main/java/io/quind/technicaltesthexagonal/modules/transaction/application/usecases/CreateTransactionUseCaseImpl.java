package io.quind.technicaltesthexagonal.modules.transaction.application.usecases;

import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;
import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountStatus;
import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountType;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;
import io.quind.technicaltesthexagonal.modules.transaction.domain.dtos.TransactionRequest;
import io.quind.technicaltesthexagonal.modules.transaction.domain.dtos.TransactionResponse;
import io.quind.technicaltesthexagonal.modules.transaction.domain.mappers.TransactionMapper;
import io.quind.technicaltesthexagonal.modules.transaction.domain.models.Transaction;
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

        Transaction transaction = TransactionMapper.fromTransactionRequest(transactionRequest);

        switch(transaction.getTransactionType()) {
            case CONSIGNMENT:
                consignment(transaction);
                break;
            case WITHDRAWAL:
                withdrawal(transaction);
                break;
            case TRANSFER:
                transfer(transaction);
                break;
        }

        return TransactionMapper.toTransactionResponse(transactionRepositoryPort.createTransaction(transaction));
    }

    private void consignment(Transaction transaction){
        Account originAccount = getAccount(transaction.getOriginAccountNumber());
        verifyAccountStatus(originAccount);
        BigDecimal newBalance = originAccount.getBalance().add(transaction.getAmount());
        originAccount.setBalance(newBalance);
        transaction.setOriginAccount(accountRepositoryPort.save(originAccount));
    }

    private void withdrawal(Transaction transaction){
        Account originAccount = getAccount(transaction.getOriginAccountNumber());
        verifyAccountStatus(originAccount);
        BigDecimal newBalance = transaction.getAmount().subtract(originAccount.getBalance());
        if (originAccount.getAccountType().equals(AccountType.ACC_SAVINGS) && newBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("You do not have sufficient funds for this transaction");
        }
        originAccount.setBalance(newBalance);
        transaction.setOriginAccount(accountRepositoryPort.save(originAccount));
    }

    private void transfer(Transaction transaction){
        Account originAccount = getAccount(transaction.getOriginAccountNumber());
        verifyAccountStatus(originAccount);
        BigDecimal newBalanceOriginAccount = originAccount.getBalance().subtract(transaction.getAmount());
        if (originAccount.getAccountType().equals(AccountType.ACC_SAVINGS) && newBalanceOriginAccount.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("You do not have sufficient funds for this transaction");
        }
        originAccount.setBalance(newBalanceOriginAccount);

        Account destinationAccount = getAccount(transaction.getDestinationAccountNumber());
        verifyAccountStatus(destinationAccount);
        BigDecimal newBalanceDestinationAccount = destinationAccount.getBalance().add(transaction.getAmount());
        destinationAccount.setBalance(newBalanceDestinationAccount);

        transaction.setOriginAccount(accountRepositoryPort.save(originAccount));
        transaction.setDestinationAccount(accountRepositoryPort.save(destinationAccount));
    }

    private Account getAccount(String accountNumber){
        return accountRepositoryPort.findByAccountNumber(accountNumber).orElseThrow(()-> new RuntimeException("This account " + accountNumber + " is invalid!"));
    }

    private void verifyAccountStatus(Account account){
        if (!account.getAccountStatus().equals(AccountStatus.ACTIVE)) throw new RuntimeException("The account "+ account.getAccountNumber() + " is "+ account.getAccountStatus() + "!");
    }

}
