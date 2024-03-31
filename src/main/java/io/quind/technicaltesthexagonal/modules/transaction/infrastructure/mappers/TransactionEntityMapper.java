package io.quind.technicaltesthexagonal.modules.transaction.infrastructure.mappers;

import io.quind.technicaltesthexagonal.modules.account.infrastructure.mappers.AccountEntityMapper;
import io.quind.technicaltesthexagonal.modules.transaction.domain.models.Transaction;
import io.quind.technicaltesthexagonal.modules.transaction.domain.models.TransactionType;
import io.quind.technicaltesthexagonal.modules.transaction.infrastructure.entities.TransactionEntity;

public class TransactionEntityMapper {

    public static TransactionEntity fromTransaction(Transaction transaction){
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .amount(transaction.getAmount())
                .transactionType(transaction.getTransactionType())
                .originAccount(AccountEntityMapper.fromAccount(transaction.getOriginAccount()))
                .build();
        if (transaction.getTransactionType().equals(TransactionType.TRANSFER)){
            transactionEntity.setDestinationAccount(AccountEntityMapper.fromAccount(transaction.getDestinationAccount()));
        }
        return transactionEntity;
    }

    public static Transaction toTransaction(TransactionEntity transactionEntity){
        Transaction transaction = Transaction.builder()
                .id(transactionEntity.getId())
                .amount(transactionEntity.getAmount())
                .transactionType(transactionEntity.getTransactionType())
                .date(transactionEntity.getDate())
                .originAccountNumber(transactionEntity.getOriginAccount().getAccountNumber())
                .originAccount(AccountEntityMapper.toAccount(transactionEntity.getOriginAccount()))
                .build();
        if (transactionEntity.getTransactionType().equals(TransactionType.TRANSFER)){
            transaction.setDestinationAccount(AccountEntityMapper.toAccount(transactionEntity.getDestinationAccount()));
            transaction.setDestinationAccountNumber("%s".formatted(transactionEntity.getDestinationAccount().getAccountNumber()));
        }
        return transaction;
    }

}
