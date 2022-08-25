package com.mathewgv.library.dao.transaction;

import com.mathewgv.library.dao.*;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.dao.OrderDao;
import com.mathewgv.library.dao.RoleDao;
import com.mathewgv.library.dao.UserDao;
import com.mathewgv.library.dao.UserInfoDao;
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

    OrderCreationMapper getOrderCreationMapper();

    OrderMapper getOrderMapper();

    UserMapper getUserMapper();

    UserRegistrationMapper getUserRegistrationMapper();

    UserRoleMapper getUserRoleMapper();

    void commit() throws DaoException;

    void rollback() throws DaoException;

    Connection getConnection();
}
