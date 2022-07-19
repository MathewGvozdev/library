package com.mathewgv.library.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class LocalDateFormatter {

    private static final String PATTERN = "dd-MM-yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    private LocalDateFormatter() {
    }

    public static LocalDate format(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    public static String format(LocalDate date) {
        if (date != null) {
            return date.format(FORMATTER);
        } else {
            return null;
        }
    }

    public static boolean isValid(String date) {
        try {
            format(date);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}
