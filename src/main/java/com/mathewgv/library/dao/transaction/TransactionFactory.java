package com.mathewgv.library.dao.transaction;

import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.util.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactory {

    private static final TransactionFactory INSTANCE = new TransactionFactory();

    private TransactionFactory() {
    }

    public Transaction getTransaction() throws DaoException {
        try {
            Connection connection = ConnectionPool.get();
            connection.setAutoCommit(false);
            return new TransactionImpl(connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static TransactionFactory getInstance() {
        return INSTANCE;
    }
}
