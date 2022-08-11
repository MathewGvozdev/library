package com.mathewgv.library.service;

import com.mathewgv.library.entity.order.Order;
import com.mathewgv.library.service.dto.OrderCreationDto;
import com.mathewgv.library.service.dto.OrderDto;
import com.mathewgv.library.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderDto> findAllOrdersByClientId(Integer clientId) throws ServiceException;

    Order makeOrder(OrderCreationDto orderCreationDto) throws ServiceException;

    List<OrderDto> findAllOrders(Integer page, Integer limit) throws ServiceException;

    List<OrderDto> findAllOrders() throws ServiceException;

    Optional<OrderDto> findOrderById(Long id) throws ServiceException;

    Optional<OrderDto> findOrderIfBookIsLoaned(Integer bookId) throws ServiceException;

    void updateOrder(OrderCreationDto orderCreationDto) throws ServiceException;
}
