package io.quind.technicaltesthexagonal.modules.account.domain.models;

import lombok.Getter;

@Getter
public enum AccountStatus {
    ACTIVE ("Activa"),
    INACTIVE ("Inactiva"),
    CANCELED ("Cancelada");

    private final String description;

    AccountStatus(String description) {
        this.description = description;
    }

    }
