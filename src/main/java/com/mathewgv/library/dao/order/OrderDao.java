package com.mathewgv.library.dao.order;

import com.mathewgv.library.dao.Dao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.order.Order;

import java.util.List;

public interface OrderDao extends Dao<Long, Order> {

    List<Order> findAllOrdersByClientId(Integer clientId) throws DaoException;
}
