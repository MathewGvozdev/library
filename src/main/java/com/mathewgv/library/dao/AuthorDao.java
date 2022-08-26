package com.mathewgv.library.dao;

import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao extends Dao<Integer, Author> {

    Optional<Author> findByNameAndSurname(String firstName, String surname) throws DaoException;

    List<Author> findAllAuthorsOfTheBook(Integer bookMetaId) throws DaoException;
}
