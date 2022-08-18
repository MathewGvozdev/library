package com.mathewgv.library.service.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.book.impl.AuthorDaoImpl;
import com.mathewgv.library.dao.book.impl.GenreDaoImpl;
import com.mathewgv.library.dao.factory.DaoFactory;
import com.mathewgv.library.service.dto.BookDto;
import com.mathewgv.library.entity.book.Book;
import com.mathewgv.library.entity.book.Genre;
import com.mathewgv.library.service.mapper.Mapper;

import static java.util.stream.Collectors.joining;

public class BookMapper extends DaoConnection implements Mapper<Book, BookDto> {

    private static final BookMapper INSTANCE = new BookMapper();

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private BookMapper() {
    }

    @Override
    public BookDto mapFrom(Book object) {
        setConnectionForDependencies();
        var authorString = daoFactory.getAuthorDao().findAllAuthorsOfTheBook(object.getBookMeta().getId())
                .stream()
                .map(author -> author.getFirstName() + " " + author.getSurname())
                .collect(joining(", "));

        var genreString = daoFactory.getGenreDao().findAllGenresOfTheBook(object.getBookMeta().getId())
                .stream()
                .map(Genre::getTitle)
                .collect(joining(", "));

        return BookDto.builder()
                .id(object.getId())
                .title(object.getBookMeta().getTitle())
                .authors(authorString)
                .genres(genreString)
                .series(object.getBookMeta().getSeries())
                .publisher(object.getPublisher().getTitle())
                .pages(object.getPages())
                .publicationYear(object.getPublicationYear())
                .image(object.getBookMeta().getImage())
                .build();
    }

    private void setConnectionForDependencies() {
        AuthorDaoImpl.getInstance().setConnection(connection.get());
        GenreDaoImpl.getInstance().setConnection(connection.get());
    }

    public static BookMapper getInstance() {
        return INSTANCE;
    }
}
