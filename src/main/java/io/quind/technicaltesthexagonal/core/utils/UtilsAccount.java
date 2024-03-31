package io.quind.technicaltesthexagonal.core.utils;

import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountType;
import io.quind.technicaltesthexagonal.modules.account.domain.ports.out.AccountRepositoryPort;

import java.math.BigDecimal;
import java.util.Random;

public class UtilsAccount {

    private final AccountRepositoryPort accountRepositoryPort;

    public UtilsAccount(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    public String generateAccountNumber(AccountType accountType) {
        String accountNumber;
        do {
            if (accountType.equals(AccountType.ACC_CHECKING)){
                accountNumber = "33"+ generateRandomNumber();
            }
            else {
                accountNumber = "53"+ generateRandomNumber();
            }
        } while (existAccountNumber(accountNumber));

        return accountNumber;
    }

    public boolean isBalanceGreaterThanZero(BigDecimal balance){
        return balance.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean existAccountNumber(String accountNumber){
        return accountRepositoryPort.existsByAccountNumber(accountNumber);
    }
    private String generateRandomNumber(){
        Random random = new Random();
        StringBuilder randomNumber = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10);
            randomNumber.append(digit);
        }

        return randomNumber.toString();
    }
}
