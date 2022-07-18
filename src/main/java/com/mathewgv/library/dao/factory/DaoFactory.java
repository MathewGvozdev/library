package com.mathewgv.library.dao.factory;

import com.mathewgv.library.dao.book.*;
import com.mathewgv.library.dao.book.impl.*;
import com.mathewgv.library.dao.order.OrderDao;
import com.mathewgv.library.dao.order.impl.OrderDaoImpl;
import com.mathewgv.library.dao.user.*;
import com.mathewgv.library.dao.user.impl.RoleDaoImpl;
import com.mathewgv.library.dao.user.impl.UserDaoImpl;
import com.mathewgv.library.dao.user.impl.UserInfoDaoImpl;

public class DaoFactory {

    private static final DaoFactory INSTANCE = new DaoFactory();

    private final AuthorDao authorDao = AuthorDaoImpl.getInstance();
    private final BookMetaDao bookMetaDao = BookMetaDaoImpl.getInstance();
    private final GenreDao genreDao = GenreDaoImpl.getInstance();
    private final PublisherDao publisherDao = PublisherDaoImpl.getInstance();
    private final BookDao bookDao = BookDaoImpl.getInstance();
    private final RoleDao roleDao = RoleDaoImpl.getInstance();
    private final UserDao userDao = UserDaoImpl.getInstance();
    private final UserInfoDao userInfoDao = UserInfoDaoImpl.getInstance();
    private final OrderDao orderDao = OrderDaoImpl.getInstance();

    private DaoFactory(){
    }

    public AuthorDao getAuthorDao() {
        return authorDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public BookMetaDao getBookMetaDao() {
        return bookMetaDao;
    }

    public GenreDao getGenreDao() {
        return genreDao;
    }

    public PublisherDao getPublisherDao() {
        return publisherDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }
}
