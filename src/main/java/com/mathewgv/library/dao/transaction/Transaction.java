package com.mathewgv.library.dao.transaction;

import com.mathewgv.library.dao.book.*;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.dao.order.OrderDao;
import com.mathewgv.library.dao.user.RoleDao;
import com.mathewgv.library.dao.user.UserDao;
import com.mathewgv.library.dao.user.UserInfoDao;
import com.mathewgv.library.service.mapper.impl.*;

import java.sql.Connection;

public interface Transaction extends AutoCloseable {

    AuthorDao getAuthorDao();

    BookDao getBookDao();

    BookMetaDao getBookMetaDao();

    GenreDao getGenreDao();

    PublisherDao getPublisherDao();

    OrderDao getOrderDao();

    RoleDao getRoleDao();

    UserDao getUserDao();

    UserInfoDao getUserInfoDao();

    AuthorCreationMapper getAuthorCreationMapper();

    BookMapper getBookMapper();

    BookMetaMapper getBookMetaMapper();

    GenreCreationMapper getGenreCreationMapper();

    UserMapper getUserMapper();

    UserRegistrationMapper getUserRegistrationMapper();

    OrderCreationMapper getOrderCreationMapper();

    OrderMapper getOrderMapper();

    void commit() throws DaoException;

    void rollback() throws DaoException;

    Connection getConnection();
}
