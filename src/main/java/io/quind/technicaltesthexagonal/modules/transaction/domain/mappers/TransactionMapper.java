package io.quind.technicaltesthexagonal.modules.transaction.domain.mappers;

import io.quind.technicaltesthexagonal.modules.account.domain.mappers.AccountMapper;
import io.quind.technicaltesthexagonal.modules.transaction.domain.dtos.TransactionRequest;
import io.quind.technicaltesthexagonal.modules.transaction.domain.dtos.TransactionResponse;
import io.quind.technicaltesthexagonal.modules.transaction.domain.models.Transaction;

public class TransactionMapper {

    public static Transaction fromTransactionRequest(TransactionRequest transactionRequest){
        return Transaction.builder()
                .amount(transactionRequest.getAmount())
                .transactionType(transactionRequest.getTransactionType())
                .originAccountId(transactionRequest.getOriginAccountId())
                .destinationAccountId(transactionRequest.getDestinationAccountId())
                .build();
    }

    public static TransactionResponse toTransactionResponse(Transaction transaction){
        return TransactionResponse.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .transactionType(transaction.getTransactionType())
                .date(transaction.getDate())
                .originAccount(AccountMapper.toAccountResponse(transaction.getOriginAccount()))
                .destinationAccount(AccountMapper.toAccountResponse(transaction.getDestinationAccount()))
                .build();
    }
}
