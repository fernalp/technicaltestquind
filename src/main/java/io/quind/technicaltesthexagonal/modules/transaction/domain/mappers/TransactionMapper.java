package io.quind.technicaltesthexagonal.modules.transaction.domain.mappers;

import io.quind.technicaltesthexagonal.modules.account.domain.mappers.AccountMapper;
import io.quind.technicaltesthexagonal.modules.transaction.domain.dtos.TransactionRequest;
import io.quind.technicaltesthexagonal.modules.transaction.domain.dtos.TransactionResponse;
import io.quind.technicaltesthexagonal.modules.transaction.domain.models.Transaction;
import io.quind.technicaltesthexagonal.modules.transaction.domain.models.TransactionType;

public class TransactionMapper {

    public static Transaction fromTransactionRequest(TransactionRequest transactionRequest){
        Transaction transaction = Transaction.builder()
                .amount(transactionRequest.getAmount())
                .transactionType(transactionRequest.getTransactionType())
                .originAccountId(transactionRequest.getOriginAccountId())
                .build();
        if (transactionRequest.getTransactionType().equals(TransactionType.TRANSFER)){
            transaction.setDestinationAccountId(transactionRequest.getDestinationAccountId());
        }
        return transaction;
    }

    public static TransactionResponse toTransactionResponse(Transaction transaction){
        TransactionResponse transactionResponse = TransactionResponse.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .transactionType(transaction.getTransactionType())
                .date(transaction.getDate())
                .originAccount(AccountMapper.toAccountResponse(transaction.getOriginAccount()))
                .build();

        if (transaction.getTransactionType().equals(TransactionType.TRANSFER)){
            transactionResponse.setDestinationAccount(AccountMapper.toAccountResponse(transaction.getDestinationAccount()));
        }
        return transactionResponse;
    }
}
