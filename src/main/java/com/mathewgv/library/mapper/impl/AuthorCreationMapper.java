package com.mathewgv.library.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.service.dto.AuthorDto;
import com.mathewgv.library.entity.book.Author;
import com.mathewgv.library.mapper.Mapper;

public class AuthorCreationMapper extends DaoConnection implements Mapper<AuthorDto, Author> {

    private static final AuthorCreationMapper INSTANCE = new AuthorCreationMapper();

    private AuthorCreationMapper() {
    }

    @Override
    public Author mapFrom(AuthorDto object) {
        return new Author(
                object.getName(),
                object.getSurname());
    }

    public static AuthorCreationMapper getInstance() {
        return INSTANCE;
    }
}
