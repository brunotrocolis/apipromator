package com.trocolis.api.promator.model.entity.id;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;


@Data
public class CredentialId implements Serializable {
    private UUID userId;
    private Long sequence;
}
