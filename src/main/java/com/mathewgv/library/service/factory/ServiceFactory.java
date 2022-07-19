package com.mathewgv.library.service.factory;

import com.mathewgv.library.service.BookService;
import com.mathewgv.library.service.LibraryService;
import com.mathewgv.library.service.UserService;
import com.mathewgv.library.service.impl.BookServiceImpl;
import com.mathewgv.library.service.impl.LibraryServiceImpl;
import com.mathewgv.library.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final BookService bookService = BookServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();
    private final LibraryService libraryService = LibraryServiceImpl.getInstance();

    private ServiceFactory() {
    }

    public BookService getBookService() {
        return bookService;
    }

    public UserService getUserService() {
        return userService;
    }

    public LibraryService getLibraryService() {
        return libraryService;
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
}
