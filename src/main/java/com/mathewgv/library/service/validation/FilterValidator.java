package com.mathewgv.library.service.validation;

import com.mathewgv.library.dao.filter.BookFilter;

public class FilterValidator {

    public static void validate(BookFilter filter) {
        if ("".equals(filter.getTitle())) {
            filter.setTitle(null);
        }
        if ("".equals(filter.getAuthor())) {
            filter.setAuthor(null);
        }
        if ("".equals(filter.getGenre())) {
            filter.setGenre(null);
        }
        if ("".equals(filter.getSeries())) {
            filter.setSeries(null);
        }
    }
}
