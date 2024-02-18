package com.med.check.db.dto.request;

import com.med.check.db.validation.annotation.PasswordValid;
import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest(
        @NotBlank(message = "Пароль не должна быть пустой!")
        @PasswordValid(message = "Длина пароля должна быть более 8 символов и содержать как минимум одну заглавную букву и одну цифру!")
        String newPassword
) {
}
