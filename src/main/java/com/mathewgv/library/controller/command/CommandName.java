package com.mathewgv.library.controller.command;

public enum CommandName {

    HOME,
    ADMIN_MENU,
    WRONG_REQUEST,

    LOGIN,
    LOGOUT,
    REGISTRATION,
    CHANGE_LOCALE,

    FIND_ALL_BOOK_METAS,
    FIND_ALL_BOOK_METAS_BY_FILTER,
    MAKE_ORDER,
    FIND_USER_INFO,
    UPDATE_USER_INFO,

    FIND_ALL_ORDERS,
    FIND_ALL_BOOKS,
    FIND_BOOK_BY_ID,
    FIND_ALL_USERS,
    ADD_BOOK,
    DELETE_BOOK,
    UPDATE_ORDER,

    CHANGE_USER_ROLE;

    public String convertToParam() {
        return this.name().toLowerCase();
    }
}
