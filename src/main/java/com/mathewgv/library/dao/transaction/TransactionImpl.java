package com.mathewgv.library.dao.transaction;

import com.mathewgv.library.dao.Dao;
import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.book.*;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.dao.factory.DaoFactory;
import com.mathewgv.library.dao.order.OrderDao;
import com.mathewgv.library.dao.user.RoleDao;
import com.mathewgv.library.dao.user.UserDao;
import com.mathewgv.library.dao.user.UserInfoDao;
import com.mathewgv.library.service.mapper.Mapper;
import com.mathewgv.library.service.mapper.factory.MapperFactory;
import com.mathewgv.library.service.mapper.impl.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class TransactionImpl implements Transaction {

    private final Connection connection;

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private final MapperFactory mapperFactory = MapperFactory.getInstance();

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public AuthorDao getAuthorDao() {
        return setConnection(daoFactory.getAuthorDao());
    }

    @Override
    public BookDao getBookDao() {
        return setConnection(daoFactory.getBookDao());
    }

    @Override
    public BookMetaDao getBookMetaDao() {
        return setConnection(daoFactory.getBookMetaDao());
    }

    @Override
    public GenreDao getGenreDao() {
        return setConnection(daoFactory.getGenreDao());
    }

    @Override
    public PublisherDao getPublisherDao() {
        return setConnection(daoFactory.getPublisherDao());
    }

    @Override
    public OrderDao getOrderDao() {
        return setConnection(daoFactory.getOrderDao());
    }

    @Override
    public RoleDao getRoleDao() {
        return setConnection(daoFactory.getRoleDao());
    }

    @Override
    public UserDao getUserDao() {
        return setConnection(daoFactory.getUserDao());
    }

    @Override
    public UserInfoDao getUserInfoDao() {
        return setConnection(daoFactory.getUserInfoDao());
    }

    @Override
    public AuthorCreationMapper getAuthorCreationMapper() {
        return setConnection(mapperFactory.getAuthorCreationMapper());
    }

    @Override
    public BookMapper getBookMapper() {
        return setConnection(mapperFactory.getBookMapper());
    }

    @Override
    public BookMetaMapper getBookMetaMapper() {
        return setConnection(mapperFactory.getBookMetaMapper());
    }

    @Override
    public GenreCreationMapper getGenreCreationMapper() {
        return setConnection(mapperFactory.getGenreCreationMapper());
    }

    @Override
    public UserMapper getUserMapper() {
        return setConnection(mapperFactory.getUserMapper());
    }

    @Override
    public UserRegistrationMapper getUserRegistrationMapper() {
        return setConnection(mapperFactory.getUserRegistrationMapper());
    }

    @Override
    public OrderCreationMapper getOrderCreationMapper() {
        return setConnection(mapperFactory.getOrderCreationMapper());
    }
    @Override
    public OrderMapper getOrderMapper() {
        return setConnection(mapperFactory.getOrderMapper());
    }

    public  <A extends Dao> A setConnection(A dao) {
        ((DaoConnection) dao).setConnection(connection);
        return dao;
    }

    public  <A extends Mapper> A setConnection(A mapper) {
        ((DaoConnection) mapper).setConnection(connection);
        return mapper;
    }

    @Override
    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            log.error("Error occurred while committing the connection", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void close() throws DaoException {
        rollback();
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Error occurred while closing the connection", e);
            throw new DaoException(e);
        }

    }

    @Override
    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            log.error("Error occurred while rolling back the connection", e);
            throw new DaoException(e);
        }
    }
}
