package com.mathewgv.library.dao.book;

import com.mathewgv.library.dao.Dao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.book.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao extends Dao<Integer, Book> {

    Optional<Book> findAnyByBookMetaId(Integer bookMetaId) throws DaoException;

    List<Book> findAll(Integer page, Integer limit) throws DaoException;
}