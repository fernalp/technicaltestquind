package io.quind.technicaltesthexagonal.modules.account.domain.mappers;

import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountRequest;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;
import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;
import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountStatus;
import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountType;
import io.quind.technicaltesthexagonal.modules.customer.domain.mappers.CustomerMapper;

public class AccountMapper {

    public static Account fromAccountRequest(AccountRequest accountRequest){

        return  Account.builder()
                .accountType(AccountType.valueOf(accountRequest.getAccountType()))
                .accountStatus(AccountStatus.INACTIVE)
                .balance(accountRequest.getBalance())
                .gmfExempt(accountRequest.isGmfExempt())
                .customerId(accountRequest.getCustomerId())
                .build();

    }

    public static AccountResponse toAccountResponse(Account account){
        return AccountResponse.builder()
                .id(account.getId())
                .accountType(account.getAccountType())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .accountStatus(account.getAccountStatus())
                .gmfExempt(account.isGmfExempt())
                .customer(CustomerMapper.toCustomerResponse(account.getCustomer()))
                .build();
    }

}
