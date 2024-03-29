package io.quind.technicaltesthexagonal.modules.account.domain.models;

import lombok.Getter;

@Getter
public enum AccountType {
    ACC_CHECKING("Cuenta Corriente"),
    ACC_SAVINGS ("Cuenta de Ahorros");

    private final String description;

    AccountType(String description) {
        this.description = description;
    }

}
