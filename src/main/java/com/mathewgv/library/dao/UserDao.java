package com.mathewgv.library.dao;

import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<Integer, User> {

    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;

    void updateRole(User user) throws DaoException;
}
