package com.mathewgv.library.service.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.factory.DaoFactory;
import com.mathewgv.library.service.dto.BookDto;
import com.mathewgv.library.entity.book.BookMeta;
import com.mathewgv.library.entity.book.Genre;
import com.mathewgv.library.service.mapper.Mapper;

import static java.util.stream.Collectors.joining;

public class BookMetaMapper extends DaoConnection implements Mapper<BookMeta, BookDto> {

    private static final BookMetaMapper INSTANCE = new BookMetaMapper();

    private static final String ELEMENTS_SEPARATOR = ", ";
    private static final String AUTHOR_NAME_SEPARATOR = " ";

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private BookMetaMapper() {
    }

    @Override
    public BookDto mapFrom(BookMeta object) {
        return BookDto.builder()
                .id(object.getId())
                .title(object.getTitle())
                .authors(getAuthorsAsString(object))
                .genres(getGenresAsString(object))
                .series(object.getSeries())
                .image(object.getImage())
                .build();
    }

    private String getAuthorsAsString(BookMeta object) {
        var authorDao = daoFactory.getAuthorDao();
        return authorDao.findAllAuthorsOfTheBook(object.getId())
                .stream()
                .map(author -> author.getFirstName() + AUTHOR_NAME_SEPARATOR + author.getSurname())
                .collect(joining(ELEMENTS_SEPARATOR));
    }

    private String getGenresAsString(BookMeta object) {
        var genreDao = daoFactory.getGenreDao();
        return genreDao.findAllGenresOfTheBook(object.getId())
                .stream()
                .map(Genre::getTitle)
                .collect(joining(ELEMENTS_SEPARATOR));
    }

    public static BookMetaMapper getInstance() {
        return INSTANCE;
    }
}
