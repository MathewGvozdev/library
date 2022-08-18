package com.mathewgv.library.dao.user;

import com.mathewgv.library.dao.Dao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.user.Role;

import java.util.Optional;

public interface RoleDao extends Dao<Integer, Role> {

    Optional<Role> findByTitle(String title) throws DaoException;
}
