package com.mathewgv.library.service.validator.impl;

import com.mathewgv.library.service.dto.BookCreationDto;
import com.mathewgv.library.service.validator.Error;
import com.mathewgv.library.service.validator.ValidationResult;
import com.mathewgv.library.service.validator.Validator;

import java.util.regex.Pattern;

public class BookDtoValidator implements Validator<BookCreationDto> {

    private static final BookDtoValidator INSTANCE = new BookDtoValidator();

    private static final String TITLE_REGEX = "[А-Яа-яA-Za-z0-9,\\- ]*";
    private static final String CYRILLIC_REGEX = "[А-Яа-я,\\- ]*";

    private static final String ERROR_KEY = "invalid.filter";

    @Override
    public ValidationResult isValid(BookCreationDto object) {
        var validationResult = new ValidationResult();
        if (object.getTitle() != null && !Pattern.matches(TITLE_REGEX, object.getTitle()) ||
            object.getAuthors() != null && !Pattern.matches(CYRILLIC_REGEX, object.getAuthors()) ||
            object.getGenres() != null && !Pattern.matches(CYRILLIC_REGEX, object.getGenres()) ||
            object.getSeries() != null && !Pattern.matches(CYRILLIC_REGEX, object.getSeries()) ||
            object.getPublisher() != null && !Pattern.matches(CYRILLIC_REGEX, object.getPublisher()) ||
            object.getPublisherCity() != null && !Pattern.matches(CYRILLIC_REGEX, object.getPublisherCity())) {
            validationResult.add(new Error(ERROR_KEY));
        }
        return validationResult;
    }

    public static BookDtoValidator getInstance() {
        return INSTANCE;
    }
}
