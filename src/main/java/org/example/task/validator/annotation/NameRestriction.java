package org.example.task.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.task.validator.annotation.impl.NameRestrictionValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameRestrictionValidator.class)
public @interface NameRestriction {
    String value();

    String message() default "Invalid username.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

