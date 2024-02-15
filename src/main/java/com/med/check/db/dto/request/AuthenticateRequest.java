package com.med.check.db.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticateRequest(
        @Email(message = "Адрес электронной почты должен иметь вид example@gmail.com.")
        @NotBlank(message = "Электронная почта не должна быть пустой!")
        String email,
        @NotBlank(message = "Электронная почта не должна быть пустой!")
        String password
) {
}
