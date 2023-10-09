package com.trocolis.api.promator.model.dto.auth.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ConfirmationRequest(@NotNull @JsonProperty("user-id") UUID userId, @NotBlank String password) {
}
