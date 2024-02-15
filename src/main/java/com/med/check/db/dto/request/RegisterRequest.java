package com.med.check.db.dto.request;

import com.med.check.db.validation.annotation.NameValid;
import com.med.check.db.validation.annotation.PasswordValid;
import com.med.check.db.validation.annotation.PhoneNumberValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest (
        @NotBlank(message = "Имя не должна быть пустой!")
        @NameValid(message = "Имя должно содержать от 2 до 33 символов.")
        String name,
        @NotBlank(message = "Фамилия не должна быть пустой!")
        @NameValid(message = "Имя должно содержать от 2 до 33 символов.")
        String surName,
        @PhoneNumberValid(message = "Номер телефона должен начинаться с +996, состоять из 13 символов и должен быть действительным!")
        @NotBlank(message = "Номер телефона не должна быть пустой!")
        String telNumber,
        @NotBlank(message = "Электронная почта не должна быть пустой!")
        @Email(message = "Адрес электронной почты должен иметь вид example@gmail.com.")
        String email,
        @NotBlank(message = "Пароль не должна быть пустой!")
        @PasswordValid(message = "Длина пароля должна быть более 8 символов и содержать как минимум одну заглавную букву и одну цифру!")
        String password
){
}
