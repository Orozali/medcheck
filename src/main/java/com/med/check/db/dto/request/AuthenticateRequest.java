package com.med.check.db.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticateRequest(
        @Email(message = "validation.emailValid")
        @NotBlank(message = "validation.email")
        String email,
        @NotBlank(message = "validation.password")
        String password
) {
}
