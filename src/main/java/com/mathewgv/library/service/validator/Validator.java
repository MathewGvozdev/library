package com.mathewgv.library.service.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
