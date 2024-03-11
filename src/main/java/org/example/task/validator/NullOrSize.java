package org.example.task.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.task.validator.impl.NullOrSizeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NullOrSizeValidator.class)
public @interface NullOrSize {
    String message() default "must be null or size must be between {min} and {max}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 0;

    int max() default Integer.MAX_VALUE;
}