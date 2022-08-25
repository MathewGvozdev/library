package com.mathewgv.library.service.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.factory.DaoFactory;
import com.mathewgv.library.service.dto.BookDto;
import com.mathewgv.library.entity.Book;
import com.mathewgv.library.entity.Genre;
import com.mathewgv.library.service.mapper.Mapper;

import static java.util.stream.Collectors.joining;

public class BookMapper extends DaoConnection implements Mapper<Book, BookDto> {

    private static final BookMapper INSTANCE = new BookMapper();

    private static final String ELEMENTS_SEPARATOR = ", ";
    private static final String AUTHOR_NAME_SEPARATOR = " ";

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private BookMapper() {
    }

    @Override
    public BookDto mapFrom(Book object) {
        return BookDto.builder()
                .id(object.getId())
                .title(object.getBookMeta().getTitle())
                .authors(getAuthorsAsString(object))
                .genres(getGenresAsString(object))
                .series(object.getBookMeta().getSeries())
                .publisher(object.getPublisher().getTitle())
                .pages(object.getPages())
                .publicationYear(object.getPublicationYear())
                .image(object.getBookMeta().getImage())
                .build();
    }

    private String getGenresAsString(Book object) {
        return daoFactory.getGenreDao().findAllGenresOfTheBook(object.getBookMeta().getId())
                .stream()
                .map(Genre::getTitle)
                .collect(joining(ELEMENTS_SEPARATOR));
    }

    private String getAuthorsAsString(Book object) {
        return daoFactory.getAuthorDao().findAllAuthorsOfTheBook(object.getBookMeta().getId())
                .stream()
                .map(author -> author.getFirstName() + AUTHOR_NAME_SEPARATOR + author.getSurname())
                .collect(joining(ELEMENTS_SEPARATOR));
    }

    public static BookMapper getInstance() {
        return INSTANCE;
    }
}
