package com.mathewgv.library.dao.book;

import com.mathewgv.library.dao.Dao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.book.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao extends Dao<Integer, Author> {

    Optional<Author> findByNameAndSurname(String firstName, String surname);

    List<Author> findAllAuthorsOfTheBook(Integer bookMetaId) throws DaoException;
}
