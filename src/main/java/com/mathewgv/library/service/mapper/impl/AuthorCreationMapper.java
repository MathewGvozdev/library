package com.mathewgv.library.service.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.entity.book.Author;
import com.mathewgv.library.service.mapper.Mapper;
import com.mathewgv.library.service.dto.BookDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthorCreationMapper extends DaoConnection implements Mapper<BookDto, List<Author>> {

    private static final AuthorCreationMapper INSTANCE = new AuthorCreationMapper();

    private AuthorCreationMapper() {
    }

    @Override
    public List<Author> mapFrom(BookDto object) {
        var authorsString = Arrays.stream(object.getAuthors().split(", ")).toList();
        List<Author> authors = new ArrayList<>();
        for (String author : authorsString) {
            var nameAndSurname = author.split(" ");
            authors.add(new Author(nameAndSurname[0], nameAndSurname[1]));
        }
        return authors;
    }

    public static AuthorCreationMapper getInstance() {
        return INSTANCE;
    }
}
