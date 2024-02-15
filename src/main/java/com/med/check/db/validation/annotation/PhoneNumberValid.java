package com.med.check.db.validation.annotation;

import com.med.check.db.validation.validator.PhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE,ElementType.PARAMETER,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
@Documented
public @interface PhoneNumberValid {
    String message() default "Неправильный номер телефона";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
