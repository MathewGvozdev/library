package com.mathewgv.library;

import com.mathewgv.library.service.dto.BookDto;
import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.service.factory.ServiceFactory;

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
}
