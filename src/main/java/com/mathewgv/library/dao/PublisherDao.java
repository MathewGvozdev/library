package com.mathewgv.library.dao;

import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.Publisher;

import java.util.Optional;

public interface PublisherDao extends Dao<Integer, Publisher> {

    Optional<Publisher> findByTitleAndCity(String title, String city) throws DaoException;
}
