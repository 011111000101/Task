package org.example.task.validator.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.task.validator.NameRestriction;

public class NameRestrictionValidator implements ConstraintValidator<NameRestriction, String> {

    private String nameToCheck;

    @Override
    public void initialize(NameRestriction constraintAnnotation) {
        this.nameToCheck = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return !value.equals(nameToCheck);
    }
}