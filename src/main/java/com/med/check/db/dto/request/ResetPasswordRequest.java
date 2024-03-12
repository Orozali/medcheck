package com.med.check.db.dto.request;

import com.med.check.db.validation.annotation.PasswordValid;
import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest(
        @NotBlank(message = "validation.password")
        @PasswordValid(message = "validation.passwordValid")
        String newPassword
) {
}
