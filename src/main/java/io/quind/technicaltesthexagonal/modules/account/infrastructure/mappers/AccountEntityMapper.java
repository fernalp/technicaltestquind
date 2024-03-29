package io.quind.technicaltesthexagonal.modules.account.infrastructure.mappers;

import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;
import io.quind.technicaltesthexagonal.modules.account.infrastructure.entities.AccountEntity;
import io.quind.technicaltesthexagonal.modules.customer.infrastructure.entities.CustomerEntity;
import io.quind.technicaltesthexagonal.modules.customer.infrastructure.mappers.CustomerEntityMapper;

public class AccountEntityMapper {

    public static AccountEntity fromAccount(Account account){
        return AccountEntity.builder()
                .id(account.getId())
                .accountType(account.getAccountType())
                .accountNumber(account.getAccountNumber())
                .accountStatus(account.getAccountStatus())
                .balance(account.getBalance())
                .gmfExempt(account.isGmfExempt())
                .customer(CustomerEntity.builder().customerId(account.getCustomerId()).build())
                .build();
    }

    public static Account toAccount(AccountEntity accountEntity){
        return Account.builder()
                .id(accountEntity.getId())
                .accountType(accountEntity.getAccountType())
                .accountNumber(accountEntity.getAccountNumber())
                .balance(accountEntity.getBalance())
                .gmfExempt(accountEntity.isGmfExempt())
                .accountStatus(accountEntity.getAccountStatus())
                .createdAt(accountEntity.getCreatedAt())
                .updatedAt(accountEntity.getUpdatedAt())
                .customerId(accountEntity.getCustomer().getCustomerId())
                .customer(CustomerEntityMapper.toCustomer(accountEntity.getCustomer()))
                .build();
    }
}
