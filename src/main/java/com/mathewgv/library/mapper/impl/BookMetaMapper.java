package com.mathewgv.library.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.book.impl.AuthorDaoImpl;
import com.mathewgv.library.dao.book.impl.GenreDaoImpl;
import com.mathewgv.library.dao.factory.DaoFactory;
import com.mathewgv.library.service.dto.BookDto;
import com.mathewgv.library.entity.book.BookMeta;
import com.mathewgv.library.entity.book.Genre;
import com.mathewgv.library.mapper.Mapper;

import static java.util.stream.Collectors.joining;

public class BookMetaMapper extends DaoConnection implements Mapper<BookMeta, BookDto> {

    private static final BookMetaMapper INSTANCE = new BookMetaMapper();

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private BookMetaMapper() {
    }

    @Override
    public BookDto mapFrom(BookMeta object) {
        setConnectionForDependencies();
        String authorString = getAuthorsToString(object);
        String genreString = getGenresToString(object);

        return BookDto.builder()
                .id(object.getId())
                .title(object.getTitle())
                .authors(authorString)
                .genres(genreString)
                .series(object.getSeries())
                .build();
    }

    private String getAuthorsToString(BookMeta object) {
        var authorDao = daoFactory.getAuthorDao();
        return authorDao.findAllAuthorsOfTheBook(object.getId())
                .stream()
                .map(author -> author.getFirstName() + " " + author.getSurname())
                .collect(joining(", "));
    }

    private String getGenresToString(BookMeta object) {
        var genreDao = daoFactory.getGenreDao();
        return genreDao.findAllGenresOfTheBook(object.getId())
                .stream()
                .map(Genre::getTitle)
                .collect(joining(", "));
    }

    private void setConnectionForDependencies() {
        AuthorDaoImpl.getInstance().setConnection(connection.get());
        GenreDaoImpl.getInstance().setConnection(connection.get());
    }

    public static BookMetaMapper getInstance() {
        return INSTANCE;
    }
}
