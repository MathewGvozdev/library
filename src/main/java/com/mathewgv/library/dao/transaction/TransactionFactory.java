package com.mathewgv.library.dao.transaction;

import com.mathewgv.library.dao.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class TransactionFactory {

    private static final TransactionFactory INSTANCE = new TransactionFactory();

    private TransactionFactory() {
    }

    public Transaction getTransaction() throws DaoException {
        try {
            Connection connection = ConnectionPool.getInstance().get();
            connection.setAutoCommit(false);
            return new TransactionImpl(connection);
        } catch (SQLException e) {
            log.error("Error occurred while setting connection to auto commit 'false'", e);
            throw new DaoException(e);
        }
    }

    public static TransactionFactory getInstance() {
        return INSTANCE;
    }
}
