package com.mathewgv.library.service.validator;

public final class ValidationError {

    private final String key;

    public ValidationError(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
