package com.mathewgv.library.dao;

import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.Role;

import java.util.Optional;

public interface RoleDao extends Dao<Integer, Role> {

    Optional<Role> findByTitle(String title) throws DaoException;
}
