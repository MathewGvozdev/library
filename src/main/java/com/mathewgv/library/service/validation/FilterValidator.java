package com.mathewgv.library.service.validation;

import com.mathewgv.library.dao.filter.BookFilter;

import java.lang.reflect.Field;

public class FilterValidator {

    public static void validate(BookFilter filter) {
        if (filter.getTitle().equals("")) {
            filter.setTitle(null);
        }
        if (filter.getAuthor().equals("")) {
            filter.setAuthor(null);
        }
        if (filter.getGenre().equals("")) {
            filter.setGenre(null);
        }
        if (filter.getSeries().equals("")) {
            filter.setSeries(null);
        }
    }
}
