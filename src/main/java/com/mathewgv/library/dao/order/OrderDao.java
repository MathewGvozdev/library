package com.mathewgv.library.dao.order;

import com.mathewgv.library.dao.Dao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends Dao<Long, Order> {

    void updateStatus(Order order) throws DaoException;

    List<Order> findAllOrdersByClientId(Integer clientId) throws DaoException;

    void updateWhenBookIsReturned(Order order) throws DaoException;
}
