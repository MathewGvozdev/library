package com.mathewgv.library.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class LocalDateFormatter {

    private static final String PATTERN = "yyyy-MM-dd";
    private static final String DTO_PATTERN = "dd-MM-yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    private static final DateTimeFormatter DTO_FORMATTER = DateTimeFormatter.ofPattern(DTO_PATTERN);

    private LocalDateFormatter() {
    }

    public static LocalDate format(String date) {
        try {
            return LocalDate.parse(date, DTO_FORMATTER);
        } catch (DateTimeException e) {
            try {
                return LocalDate.parse(date, FORMATTER);
            } catch (DateTimeParseException ex) {
                return null;
            }
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static String format(LocalDate date) {
        if (date != null) {
            return date.format(DTO_FORMATTER);
        } else {
            return null;
        }
    }
}
