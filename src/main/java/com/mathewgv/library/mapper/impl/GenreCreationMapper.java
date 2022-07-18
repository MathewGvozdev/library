package com.mathewgv.library.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.service.dto.GenreDto;
import com.mathewgv.library.entity.book.Genre;
import com.mathewgv.library.mapper.Mapper;

public class GenreCreationMapper extends DaoConnection implements Mapper<GenreDto, Genre> {

    private static final GenreCreationMapper INSTANCE = new GenreCreationMapper();

    private GenreCreationMapper() {
    }

    @Override
    public Genre mapFrom(GenreDto object) {
        return new Genre(
                object.getTitle()
        );
    }

    public static GenreCreationMapper getInstance() {
        return INSTANCE;
    }
}
