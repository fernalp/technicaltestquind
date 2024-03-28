package io.quind.technicaltesthexagonal.modules.account.domain.mappers;

import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountRequest;
import io.quind.technicaltesthexagonal.modules.account.domain.dtos.AccountResponse;
import io.quind.technicaltesthexagonal.modules.account.domain.models.Account;

public class AccountMapper {

    public static Account fromAccountRequest(AccountRequest accountRequest){

        return  Account.builder()
                .accountType(accountRequest.getAccountType())
                .balance(accountRequest.getBalance())
                .gmfExempt(accountRequest.isGmfExempt())
                .customer(accountRequest.getCustomer())
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
                .customer(account.getCustomer())
                .build();
    }

}
