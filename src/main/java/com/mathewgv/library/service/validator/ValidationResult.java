package com.mathewgv.library.service.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    private final List<ValidationError> validationErrors = new ArrayList<>();

    public void add(ValidationError validationError) {
        this.validationErrors.add(validationError);
    }

    public boolean hasErrors() {
        return !validationErrors.isEmpty();
    }

    public List<ValidationError> getErrors() {
        return validationErrors;
    }
}
