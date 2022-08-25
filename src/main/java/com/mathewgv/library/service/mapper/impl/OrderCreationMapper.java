package com.mathewgv.library.service.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.factory.DaoFactory;
import com.mathewgv.library.entity.LoanType;
import com.mathewgv.library.entity.Order;
import com.mathewgv.library.entity.OrderStatus;
import com.mathewgv.library.service.mapper.Mapper;
import com.mathewgv.library.service.dto.OrderCreationDto;
import com.mathewgv.library.util.LocalDateFormatter;

public class OrderCreationMapper extends DaoConnection implements Mapper<OrderCreationDto, Order> {

    private static final OrderCreationMapper INSTANCE = new OrderCreationMapper();

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private OrderCreationMapper() {
    }

    @Override
    public Order mapFrom(OrderCreationDto object) {
        var userDao = daoFactory.getUserDao();
        var bookDao = daoFactory.getBookDao();
        return new Order(object.getId(),
                userDao.findById(object.getUserId()).orElse(null),
                bookDao.findById(object.getBookId()).orElse(null),
                LocalDateFormatter.format(object.getIssueDate()),
                LocalDateFormatter.format(object.getDueDate()),
                LocalDateFormatter.format(object.getFactDate()),
                LoanType.findByName(object.getLoanType()),
                OrderStatus.findByName(object.getStatus())
                );
    }

    public static OrderCreationMapper getInstance() {
        return INSTANCE;
    }
}
