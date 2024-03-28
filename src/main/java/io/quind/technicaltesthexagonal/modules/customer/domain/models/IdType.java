package io.quind.technicaltesthexagonal.modules.customer.domain.models;

public enum IdType {
    CC("Cédula de Ciudadanía"),
    NIT("Número de Identificación Tributario"),
    CE("Cédula de Extranjería"),
    PA("Pasaporte");

    private final String description;

    IdType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
