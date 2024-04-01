package com.med.check.db.dto.request;

import com.med.check.db.validation.annotation.NameValid;
import com.med.check.db.validation.annotation.PasswordValid;
import com.med.check.db.validation.annotation.PhoneNumberValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest (
        @NotBlank(message = "validation.name")
        @NameValid(message = "validation.nameValid")
        String name,
        @NotBlank(message = "validation.surName")
        @NameValid(message = "validation.nameValid")
        String surName,
        @PhoneNumberValid(message = "validation.phoneNumberValid")
        @NotBlank(message = "validation.telNumber")
        String telNumber,
        @NotBlank(message = "validation.email")
        @Email(message = "validation.emailValid")
        String email,
        @NotBlank(message = "validation.password")
        @PasswordValid(message = "validation.passwordValid")
        String password
){
}