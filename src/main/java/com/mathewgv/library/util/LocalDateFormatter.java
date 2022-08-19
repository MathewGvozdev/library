package com.mathewgv.library.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class LocalDateFormatter {

    private static final String PATTERN = "yyyy-MM-dd";
    private static final String PATTERN_DTO = "dd-MM-yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    private static final DateTimeFormatter FORMATTER_DTO = DateTimeFormatter.ofPattern(PATTERN_DTO);

    private LocalDateFormatter() {
    }

    public static LocalDate format(String date) {
        try {
            return LocalDate.parse(date, FORMATTER);
        } catch (DateTimeException | NullPointerException e) {
            return null;
        }
    }

    public static LocalDate formatToDto(String date) {
        try {
            return LocalDate.parse(date, FORMATTER_DTO);
        } catch (DateTimeException | NullPointerException e) {
            return null;
        }
    }

    public static String format(LocalDate date) {
        if (date != null) {
            return date.format(FORMATTER_DTO);
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
