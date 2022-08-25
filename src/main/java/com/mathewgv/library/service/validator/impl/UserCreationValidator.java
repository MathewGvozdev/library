package com.mathewgv.library.service.validator.impl;

import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.service.validator.Error;
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

    @Override
    public ValidationResult isValid(UserCreationDto object) {
        var validationResult = new ValidationResult();
        if (object.getFirstName() != null && !Pattern.matches(NAME_REGEX, object.getFirstName())) {
            validationResult.add(new Error("invalid.registration.name"));
        }
        if (object.getSurname() != null && !Pattern.matches(NAME_REGEX, object.getSurname())) {
            validationResult.add(new Error("invalid.registration.surname"));
        }
        if (object.getLogin() != null && !Pattern.matches(LOGIN_REGEX, object.getLogin())) {
            validationResult.add(new Error("invalid.registration.login"));
        }
        if (object.getTelephone() != null && !Pattern.matches(TELEPHONE_REGEX, object.getTelephone())) {
            validationResult.add(new Error("invalid.registration.telephone"));
        }
        if (object.getPassportNumber() != null && !Pattern.matches(PASSPORT_REGEX, object.getPassportNumber())) {
            validationResult.add(new Error("invalid.registration.passport"));
        }
        if (object.getPassword() != null && !Pattern.matches(PASSWORD_REGEX, object.getPassword())) {
            validationResult.add(new Error("invalid.registration.password"));
        }
        return validationResult;
    }

    public static UserCreationValidator getInstance() {
        return INSTANCE;
    }
}
