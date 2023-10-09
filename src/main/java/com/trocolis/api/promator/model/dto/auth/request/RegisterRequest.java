package com.trocolis.api.promator.model.dto.auth.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterRequest(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotNull @JsonFormat(pattern="yyyy-MM-dd") @JsonProperty("birth-date") LocalDate birthDate
) {
}
