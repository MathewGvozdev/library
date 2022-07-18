package com.mathewgv.library.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.book.impl.AuthorDaoImpl;
import com.mathewgv.library.dao.book.impl.GenreDaoImpl;
import com.mathewgv.library.dao.factory.DaoFactory;
import com.mathewgv.library.service.dto.BookMetaDto;
import com.mathewgv.library.entity.book.Author;
import com.mathewgv.library.entity.book.BookMeta;
import com.mathewgv.library.entity.book.Genre;
import com.mathewgv.library.mapper.Mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookMetaCreationMapper extends DaoConnection implements Mapper<BookMetaDto, BookMeta> {

    private static final BookMetaCreationMapper INSTANCE = new BookMetaCreationMapper();

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private static final Integer NAME_POSITION = 0;
    private static final Integer SURNAME_POSITION = 1;

    private BookMetaCreationMapper(){
    }

    @Override
    public BookMeta mapFrom(BookMetaDto object) {
        setConnectionForDependencies();
        List<Author> authors = getAuthorsFromDto(object);
        List<Genre> genres = getGenresFromDto(object);
        return new BookMeta(
                object.getTitle(),
                object.getSeries(),
                authors,
                genres
        );
    }

    private List<Author> getAuthorsFromDto(BookMetaDto object) {
        var authorList = Arrays.stream(object.getAuthors().split(", ")).toList();
        List<Author> authors = new ArrayList<>();
        for (String authorString : authorList) {
            var nameAndSurname = authorString.split(" ");
            var firstName = nameAndSurname[NAME_POSITION];
            var surname = nameAndSurname[SURNAME_POSITION];
            var authorDao = daoFactory.getAuthorDao();
            authors.add(authorDao.findByNameAndSurname(firstName, surname).orElse(null));
        }
        return authors;
    }

    private List<Genre> getGenresFromDto(BookMetaDto object) {
        var genreList = Arrays.stream(object.getGenres().split(", ")).toList();
        List<Genre> genres = new ArrayList<>();
        for (String genreString : genreList) {
            var genreDao = daoFactory.getGenreDao();
            genres.add(genreDao.findByTitle(genreString).orElse(null));
        }
        return genres;
    }

    private void setConnectionForDependencies() {
        AuthorDaoImpl.getInstance().setConnection(connection.get());
        GenreDaoImpl.getInstance().setConnection(connection.get());
    }

    public static BookMetaCreationMapper getInstance() {
        return INSTANCE;
    }
}
