package com.mathewgv.library.service.validator.impl;

import com.mathewgv.library.service.dto.BookCreationDto;
import com.mathewgv.library.service.validator.Error;
import com.mathewgv.library.service.validator.ValidationResult;
import com.mathewgv.library.service.validator.Validator;

import java.util.regex.Pattern;

public class BookDtoValidator implements Validator<BookCreationDto> {

    private static final BookDtoValidator INSTANCE = new BookDtoValidator();

    private static final String BOOK_CREATION_REGEX = "[А-Яа-яA-Za-z0-9,\\- ]*";

    @Override
    public ValidationResult isValid(BookCreationDto object) {
        var validationResult = new ValidationResult();
        if (object.getTitle() != null && !Pattern.matches(BOOK_CREATION_REGEX, object.getTitle()) ||
            object.getAuthors() != null && !Pattern.matches(BOOK_CREATION_REGEX, object.getAuthors()) ||
            object.getGenres() != null && !Pattern.matches(BOOK_CREATION_REGEX, object.getGenres()) ||
            object.getSeries() != null && !Pattern.matches(BOOK_CREATION_REGEX, object.getSeries()) ||
            object.getPublisher() != null && !Pattern.matches(BOOK_CREATION_REGEX, object.getPublisher()) ||
            object.getPublisherCity() != null && !Pattern.matches(BOOK_CREATION_REGEX, object.getPublisherCity())) {
            validationResult.add(new Error("invalid.filter"));
        }
        return validationResult;
    }

    public static BookDtoValidator getInstance() {
        return INSTANCE;
    }
}
