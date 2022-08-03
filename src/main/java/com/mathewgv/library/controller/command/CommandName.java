package com.mathewgv.library.controller.command;

public enum CommandName {

    LOGIN,
    LOGOUT,
    REGISTRATION,
    FIND_USER_INFO,
    CHANGE_LOCALE,

    FIND_ALL_ORDERS,
    MAKE_ORDER,
    UPDATE_ORDER,
    UPDATE_USER_INFO,

    ADD_BOOK,
    DELETE_BOOK,
    FIND_ALL_BOOK_METAS,
    FIND_ALL_BOOK_METAS_BY_FILTER,
    FIND_ALL_BOOKS,
    FIND_BOOK_BY_ID,

    HOME,
    ADMIN_MENU,
    FIND_ALL_USERS,
    WRONG_REQUEST;

    public String convertToString() {
        return this.name().toLowerCase();
    }
}
