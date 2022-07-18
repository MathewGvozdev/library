package com.mathewgv.library.dao;

import java.sql.Connection;

public abstract class DaoConnection {

    protected ThreadLocal<Connection> connection;

    public void setConnection(Connection connection) {
        this.connection = ThreadLocal.withInitial(() -> connection);
    }
}
