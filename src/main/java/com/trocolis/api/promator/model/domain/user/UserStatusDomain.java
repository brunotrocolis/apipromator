package com.trocolis.api.promator.model.domain.user;

public enum UserStatusDomain {
    ACTIVE ("Ativo"),
    INACTIVE("Pendente de confirmação"),
    BLOCKED("Bloqueado"),
    DELETED("Excluído"),
    BANNED("Banido");

    private final String description;

    UserStatusDomain(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
