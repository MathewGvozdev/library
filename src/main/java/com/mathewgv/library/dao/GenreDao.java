package com.mathewgv.library.dao;

import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao extends Dao<Integer, Genre> {

    Optional<Genre> findByTitle(String title) throws DaoException;

    List<Genre> findAllGenresOfTheBook(Integer bookMetaId) throws DaoException;
}
