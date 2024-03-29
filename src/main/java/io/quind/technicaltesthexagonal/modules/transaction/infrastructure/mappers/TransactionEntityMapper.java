package io.quind.technicaltesthexagonal.modules.transaction.infrastructure.mappers;

import io.quind.technicaltesthexagonal.modules.account.infrastructure.mappers.AccountEntityMapper;
import io.quind.technicaltesthexagonal.modules.transaction.domain.models.Transaction;
import io.quind.technicaltesthexagonal.modules.transaction.infrastructure.entities.TransactionEntity;

public class TransactionEntityMapper {

    public static TransactionEntity fromTransaction(Transaction transaction){
        return TransactionEntity.builder()
                .amount(transaction.getAmount())
                .transactionType(transaction.getTransactionType())
                .originAccount(AccountEntityMapper.fromAccount(transaction.getOriginAccount()))
                .destinationAccount(AccountEntityMapper.fromAccount(transaction.getDestinationAccount()))
                .build();
    }

    public static Transaction toTransaction(TransactionEntity transactionEntity){
        return Transaction.builder()
                .id(transactionEntity.getId())
                .amount(transactionEntity.getAmount())
                .transactionType(transactionEntity.getTransactionType())
                .date(transactionEntity.getDate())
                .originAccountId(transactionEntity.getOriginAccount().getAccountNumber())
                .destinationAccountId("%s".formatted(transactionEntity.getDestinationAccount().getAccountNumber()))
                .originAccount(AccountEntityMapper.toAccount(transactionEntity.getOriginAccount()))
                .destinationAccount(AccountEntityMapper.toAccount(transactionEntity.getDestinationAccount()))
                .build();
    }

}
