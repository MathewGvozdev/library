package com.mathewgv.library.service.validator.impl;

import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.service.validator.ValidationError;
import com.mathewgv.library.service.validator.ValidationResult;
import com.mathewgv.library.service.validator.Validator;

import java.util.regex.Pattern;

public class UserCreationValidator implements Validator<UserCreationDto> {

    private static final UserCreationValidator INSTANCE = new UserCreationValidator();

    private static final String NAME_REGEX = "[А-Я][а-я]+";
    private static final String LOGIN_REGEX = "[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]";
    private static final String TELEPHONE_REGEX = "\\+375\\d{9}";
    private static final String PASSPORT_REGEX = "[A-Z0-9]{9}";
    private static final String PASSWORD_REGEX = "\\w{5,}";

    private static final String ERROR_NAME_KEY = "invalid.registration.name";
    private static final String ERROR_SURNAME_KEY = "invalid.registration.surname";
    private static final String ERROR_LOGIN_KEY = "invalid.registration.login";
    private static final String ERROR_TELEPHONE_KEY = "invalid.registration.telephone";
    private static final String ERROR_PASSPORT_KEY = "invalid.registration.passport";
    private static final String ERROR_PASSWORD_KEY = "invalid.registration.password";

    @Override
    public ValidationResult isValid(UserCreationDto object) {
        var validationResult = new ValidationResult();
        if (object.getFirstName() != null && !Pattern.matches(NAME_REGEX, object.getFirstName())) {
            validationResult.add(new ValidationError(ERROR_NAME_KEY));
        }
        if (object.getSurname() != null && !Pattern.matches(NAME_REGEX, object.getSurname())) {
            validationResult.add(new ValidationError(ERROR_SURNAME_KEY));
        }
        if (object.getLogin() != null && !Pattern.matches(LOGIN_REGEX, object.getLogin())) {
            validationResult.add(new ValidationError(ERROR_LOGIN_KEY));
        }
        if (object.getTelephone() != null && !Pattern.matches(TELEPHONE_REGEX, object.getTelephone())) {
            validationResult.add(new ValidationError(ERROR_TELEPHONE_KEY));
        }
        if (object.getPassportNumber() != null && !Pattern.matches(PASSPORT_REGEX, object.getPassportNumber())) {
            validationResult.add(new ValidationError(ERROR_PASSPORT_KEY));
        }
        if (object.getPassword() != null && !Pattern.matches(PASSWORD_REGEX, object.getPassword())) {
            validationResult.add(new ValidationError(ERROR_PASSWORD_KEY));
        }
        return validationResult;
    }

    public static UserCreationValidator getInstance() {
        return INSTANCE;
    }
}
