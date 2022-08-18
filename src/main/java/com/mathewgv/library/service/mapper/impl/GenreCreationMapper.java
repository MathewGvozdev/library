package com.mathewgv.library.service.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.service.dto.BookCreationDto;
import com.mathewgv.library.service.dto.BookDto;
import com.mathewgv.library.entity.book.Genre;
import com.mathewgv.library.service.mapper.Mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenreCreationMapper extends DaoConnection implements Mapper<BookCreationDto, List<Genre>> {

    private static final GenreCreationMapper INSTANCE = new GenreCreationMapper();

    private GenreCreationMapper() {
    }

    @Override
    public List<Genre> mapFrom(BookCreationDto object) {
        var genresString = Arrays.stream(object.getGenres().split(", ")).toList();
        List<Genre> genres = new ArrayList<>();
        for (String genre : genresString) {
            genres.add(new Genre(genre));
        }
        return genres;
    }

    public static GenreCreationMapper getInstance() {
        return INSTANCE;
    }
}
