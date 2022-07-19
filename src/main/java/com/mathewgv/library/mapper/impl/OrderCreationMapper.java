package com.mathewgv.library.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.book.impl.BookDaoImpl;
import com.mathewgv.library.dao.factory.DaoFactory;
import com.mathewgv.library.dao.user.impl.UserDaoImpl;
import com.mathewgv.library.entity.order.LoanType;
import com.mathewgv.library.entity.order.Order;
import com.mathewgv.library.entity.order.OrderStatus;
import com.mathewgv.library.mapper.Mapper;
import com.mathewgv.library.service.dto.OrderCreationDto;
import com.mathewgv.library.util.LocalDateFormatter;

import java.time.DateTimeException;

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
        try {
            var user = userDao.findById(object.getUserId());
            var book = bookDao.findById(object.getBookId());
            if (user.isPresent() && book.isPresent()) {
                return new Order(
                        object.getId(),
                        user.get(),
                        book.get(),
                        LocalDateFormatter.format(object.getIssueDate()),
                        LocalDateFormatter.format(object.getDueDate()),
                        LocalDateFormatter.format(object.getFactDate()),
                        LoanType.valueOf(object.getLoanType()),
                        OrderStatus.valueOf(object.getStatus())
                );
            } else {
                return new Order(
                        object.getId(),
                        LocalDateFormatter.format(object.getFactDate()),
                        OrderStatus.valueOf(object.getStatus())
               );
            }
        } catch (DateTimeException e) {
            return new Order(
                    object.getId(),
                    OrderStatus.valueOf(object.getStatus())
            );
        }
    }

    private void setConnectionForDependencies() {
        UserDaoImpl.getInstance().setConnection(connection.get());
        BookDaoImpl.getInstance().setConnection(connection.get());
    }

    public static OrderCreationMapper getInstance() {
        return INSTANCE;
    }
}
