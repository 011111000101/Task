package org.example.task.validator.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.task.validator.NullOrSize;

import java.util.Objects;

public class NullOrSizeValidator implements ConstraintValidator<NullOrSize, String> {
    private int min;
    private int max;

    @Override
    public void initialize(NullOrSize constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.equals(value, "")) {
            return true;
        }

        int length = value.length();
        return length >= min && length <= max;
    }
}