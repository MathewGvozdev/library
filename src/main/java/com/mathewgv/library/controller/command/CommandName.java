package com.mathewgv.library.controller.command;

public enum CommandName {

    LOGIN,
    LOGOUT,
    REGISTER,
    FIND_USER_INFO,
    CHANGE_LOCALE,

    FIND_ALL_ORDERS,
    MAKE_ORDER,
    UPDATE_ORDER,

    ADD_AUTHOR,
    ADD_BOOK,
    ADD_BOOK_META,
    ADD_GENRE,
    ADD_PUBLISHER,
    DELETE_BOOK,
    FIND_ALL_BOOK_METAS,
    FIND_ALL_BOOK_METAS_BY_FILTER,
    FIND_ALL_BOOKS,
    FIND_ANY_BOOK,
    FIND_BOOK_BY_ID,

    HOME,
    WRONG_REQUEST
}
