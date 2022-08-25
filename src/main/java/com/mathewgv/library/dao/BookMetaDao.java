package com.mathewgv.library.dao;

import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.dao.filter.BookFilter;
import com.mathewgv.library.entity.BookMeta;

import java.util.List;
import java.util.Optional;

public interface BookMetaDao extends Dao<Integer, BookMeta> {

    List<BookMeta> findAllByFilter(BookFilter filter);

    List<BookMeta> findAllWithLimit(Integer page, Integer limit) throws DaoException;

    Optional<BookMeta> findByTitle(String title) throws DaoException;
}
