package com.mathewgv.library.dao.exception;

import java.sql.SQLException;

public class DaoException extends RuntimeException {

    private final String message;

    public DaoException(SQLException e) {
        message = e.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
