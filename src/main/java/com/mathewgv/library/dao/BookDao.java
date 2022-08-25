package com.mathewgv.library.dao;

import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao extends Dao<Integer, Book> {

    List<Book> findAllWithLimit(Integer page, Integer limit) throws DaoException;

    Optional<Book> findAnyByBookMetaId(Integer bookMetaId) throws DaoException;
}