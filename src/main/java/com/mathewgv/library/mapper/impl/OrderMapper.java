package com.mathewgv.library.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.book.impl.BookDaoImpl;
import com.mathewgv.library.dao.factory.DaoFactory;
import com.mathewgv.library.dao.user.impl.UserDaoImpl;
import com.mathewgv.library.dao.user.impl.UserInfoDaoImpl;
import com.mathewgv.library.entity.order.Order;
import com.mathewgv.library.mapper.Mapper;
import com.mathewgv.library.service.dto.OrderCreationDto;
import com.mathewgv.library.service.dto.OrderDto;
import com.mathewgv.library.util.LocalDateFormatter;

public class OrderMapper extends DaoConnection implements Mapper<Order, OrderDto> {

    private static final OrderMapper INSTANCE = new OrderMapper();

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private OrderMapper() {
    }

    @Override
    public OrderDto mapFrom(Order object) {
        setConnectionForDependencies();
        var userInfoDao = daoFactory.getUserInfoDao();
        var userInfo = userInfoDao.findInfoByUserId(object.getClient().getId()).orElse(null);
        if (userInfo != null) {
            return OrderDto.builder()
                    .id(object.getId())
                    .client(userInfo.getFirstName() + " " + userInfo.getSurname())
                    .bookId(object.getBook().getId())
                    .bookTitle(object.getBook().getBookMeta().getTitle())
                    .issueDate(LocalDateFormatter.format(object.getIssueDate()))
                    .dueDate(LocalDateFormatter.format(object.getDueDate()))
                    .factDate(LocalDateFormatter.format(object.getFactDate()))
                    .loanType(object.getType().getValue())
                    .status(object.getStatus().getValue())
                    .build();
        } else {
            return null;
        }
    }

    private void setConnectionForDependencies() {
        UserInfoDaoImpl.getInstance().setConnection(connection.get());
    }

    public static OrderMapper getInstance() {
        return INSTANCE;
    }
}
