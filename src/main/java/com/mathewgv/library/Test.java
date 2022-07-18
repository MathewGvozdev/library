package com.mathewgv.library;

import com.mathewgv.library.dao.book.impl.*;
import com.mathewgv.library.entity.book.*;
import com.mathewgv.library.service.dto.BookDto;
import com.mathewgv.library.service.dto.BookMetaDto;
import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.ConnectionPool;

import java.util.*;

public class Test {

    public static void main(String[] args) {
//        bookMetaTest();
//        login();
//        register();
        var bookDto = BookDto.builder()
                .bookMetaId(14)
                .publisherId(3)
                .publicationYear(2014)
                .pages(301)
                .build();
        var book = ServiceFactory.getInstance().getBookService().addBook(bookDto);
        System.out.println(book);
    }

    private static void register() {
        var userCreationDto = UserCreationDto.builder()
                .firstName("Арбуз")
                .surname("Арбузович")
                .login("arbuz")
                .password("garbuz123")
                .telephone("+375 29 455-65-98")
                .passportNumber("BM8195012")
                .build();
        var register = ServiceFactory.getInstance().getUserService().register(userCreationDto);

        System.out.println(register);
    }

    private static void login() {
        var mathew = ServiceFactory.getInstance().getUserService().login("mathew", "123");
        System.out.println(mathew);
    }

    private static void bookMetaTest() {
        BookMetaDto object = BookMetaDto.builder()
                .authors("Илья Ильф, Евгений Петров")
                .genres("Классическая русская, Комедия")
                .title("Золотой теленок")
                .build();

        var authorList = Arrays.stream(object.getAuthors().split(", ")).toList();
        var authors = new ArrayList<Author>();
        for (String authorString : authorList) {
            var nameAndSurname = authorString.split(" ");
            var firstName = nameAndSurname[0];
            var surname = nameAndSurname[1];
            var instance = AuthorDaoImpl.getInstance();
            instance.setConnection(ConnectionPool.get());
            var author = instance.findByNameAndSurname(firstName, surname);
            authors.add(author.orElse(null));
        }

        var genreList = Arrays.stream(object.getGenres().split(", ")).toList();
        var genres = new ArrayList<Genre>();
        for (String genreString : genreList) {
            var instance = GenreDaoImpl.getInstance();
            instance.setConnection(ConnectionPool.get());
            var genre = instance.findByTitle(genreString);
            genres.add(genre.orElse(null));
        }


        var bookMeta = new BookMeta(object.getTitle(), authors, genres);
        System.out.println(bookMeta);
    }
}
