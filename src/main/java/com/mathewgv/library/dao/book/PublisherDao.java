package com.mathewgv.library.dao.book;

import com.mathewgv.library.dao.Dao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.book.Publisher;

import java.util.Optional;

public interface PublisherDao extends Dao<Integer, Publisher> {

    Optional<Publisher> findByTitleAndCity(String title, String city) throws DaoException;
}
