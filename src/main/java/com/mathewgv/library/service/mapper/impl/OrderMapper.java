package com.mathewgv.library.service.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.factory.DaoFactory;
import com.mathewgv.library.dao.user.impl.UserInfoDaoImpl;
import com.mathewgv.library.entity.order.Order;
import com.mathewgv.library.service.mapper.Mapper;
import com.mathewgv.library.service.dto.OrderDto;
import com.mathewgv.library.util.LocalDateFormatter;

public class OrderMapper extends DaoConnection implements Mapper<Order, OrderDto> {

    private static final OrderMapper INSTANCE = new OrderMapper();

    private static final String USER_NAME_SEPARATOR = " ";

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private OrderMapper() {
    }

    @Override
    public OrderDto mapFrom(Order object) {
        var userInfoDao = daoFactory.getUserInfoDao();
        var userInfo = userInfoDao.findInfoByUserId(object.getClient().getId());
        return userInfo.map(info -> OrderDto.builder()
                .id(object.getId())
                .clientId(object.getClient().getId())
                .client(info.getFirstName() + USER_NAME_SEPARATOR + info.getSurname())
                .bookId(object.getBook().getId())
                .bookTitle(object.getBook().getBookMeta().getTitle())
                .issueDate(LocalDateFormatter.format(object.getIssueDate()))
                .dueDate(LocalDateFormatter.format(object.getDueDate()))
                .factDate(LocalDateFormatter.format(object.getFactDate()))
                .loanType(object.getType().getValue())
                .status(object.getStatus().getValue())
                .build()).orElse(null);
    }

    public static OrderMapper getInstance() {
        return INSTANCE;
    }
}
