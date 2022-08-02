package com.mathewgv.library.service.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.book.impl.BookDaoImpl;
import com.mathewgv.library.dao.factory.DaoFactory;
import com.mathewgv.library.dao.user.impl.UserDaoImpl;
import com.mathewgv.library.entity.order.LoanType;
import com.mathewgv.library.entity.order.Order;
import com.mathewgv.library.entity.order.OrderStatus;
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
        setConnectionForDependencies();
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

    private void setConnectionForDependencies() {
        UserDaoImpl.getInstance().setConnection(connection.get());
        BookDaoImpl.getInstance().setConnection(connection.get());
    }

    public static OrderCreationMapper getInstance() {
        return INSTANCE;
    }
}