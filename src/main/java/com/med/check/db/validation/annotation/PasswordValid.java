package com.med.check.db.validation.annotation;

import com.med.check.db.validation.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.METHOD,ElementType.TYPE,ElementType.PARAMETER,ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordValid {
    String message() default "Неверный пароль!";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
