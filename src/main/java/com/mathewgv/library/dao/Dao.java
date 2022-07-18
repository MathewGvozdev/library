package com.mathewgv.library.dao;

import com.mathewgv.library.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {

    E create(E entity) throws DaoException;

    List<E> findAll() throws DaoException;

    Optional<E> findById(K id) throws DaoException;

    void update(E entity) throws DaoException;

    boolean delete(K id) throws DaoException;
}
