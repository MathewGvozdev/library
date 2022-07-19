package com.mathewgv.library.dao.user;

import com.mathewgv.library.dao.Dao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.user.User;

import java.util.Optional;

public interface UserDao extends Dao<Integer, User> {

    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;
}
