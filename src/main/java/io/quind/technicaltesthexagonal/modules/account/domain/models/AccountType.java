package io.quind.technicaltesthexagonal.modules.account.domain.models;

import lombok.Getter;

@Getter
public enum AccountType {
    CTA_CHECKING ("Cuenta Corriente"),
    CTA_SAVINGS ("Cuenta de Ahorros");

    private final String description;

    AccountType(String description) {
        this.description = description;
    }

}
