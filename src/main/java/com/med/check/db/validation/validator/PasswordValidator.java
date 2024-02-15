package com.med.check.db.validation.validator;

import com.med.check.db.validation.annotation.PasswordValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValid,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}$");
    }
}
