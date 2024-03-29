package io.quind.technicaltesthexagonal.modules.account.application.utils;

import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountType;

import java.math.BigDecimal;
import java.util.Random;

public class UtilsAccount {

    public static String generateAccountNumber(
            AccountType accountType
    ) {
        if (accountType.equals(AccountType.ACC_CHECKING)){
            return "33"+generateRandomNumber();
        }
        else {
            return "53"+generateRandomNumber();
        }
    }

    public static void isBalanceGreaterThanZero(BigDecimal balance){
        if (balance.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("The account cannot have negative funds");
        }
    }

    private static String generateRandomNumber(){
        Random random = new Random();
        StringBuilder randomNumber = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10);
            randomNumber.append(digit);
        }

        return randomNumber.toString();
    }
}
