package com.trocolis.api.promator.model.domain.user;

import lombok.Getter;

@Getter
public enum UserStatusDomain {
    ACTIVE ("Active"),
    INACTIVE("Pending confirmation"),
    BLOCKED("Blocked"),
    DELETED("Deleted"),
    BANNED("Banned"),;

    private final String description;

    UserStatusDomain(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User " + this.description;
    }
}
