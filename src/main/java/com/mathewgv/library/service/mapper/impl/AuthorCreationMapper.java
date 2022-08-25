package com.mathewgv.library.service.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.entity.Author;
import com.mathewgv.library.service.dto.BookCreationDto;
import com.mathewgv.library.service.mapper.Mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthorCreationMapper extends DaoConnection implements Mapper<BookCreationDto, List<Author>> {

    private static final AuthorCreationMapper INSTANCE = new AuthorCreationMapper();

    private static final String AUTHORS_SEPARATOR = ", ";
    private static final String AUTHOR_NAME_SEPARATOR = " ";
    private static final Integer NAME_POSITION = 0;
    private static final Integer SURNAME_POSITION = 1;

    private AuthorCreationMapper() {
    }

    @Override
    public List<Author> mapFrom(BookCreationDto object) {
        var authorsString = Arrays.stream(object.getAuthors().split(AUTHORS_SEPARATOR)).toList();
        List<Author> authors = new ArrayList<>();
        for (String author : authorsString) {
            var nameAndSurname = author.split(AUTHOR_NAME_SEPARATOR);
            authors.add(new Author(nameAndSurname[NAME_POSITION], nameAndSurname[SURNAME_POSITION]));
        }
        return authors;
    }

    public static AuthorCreationMapper getInstance() {
        return INSTANCE;
    }
}
