package com.mathewgv.library.service.impl;

import com.mathewgv.library.dao.transaction.TransactionFactory;
import com.mathewgv.library.entity.Order;
import com.mathewgv.library.service.OrderService;
import com.mathewgv.library.service.dto.OrderCreationDto;
import com.mathewgv.library.service.dto.OrderDto;
import com.mathewgv.library.service.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
public class OrderServiceImpl implements OrderService {

    private static final OrderServiceImpl INSTANCE = new OrderServiceImpl();

    private final TransactionFactory transactionFactory = TransactionFactory.getInstance();

    @Override
    public List<OrderDto> findAllOrders() throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var orderDao = transaction.getOrderDao();
            var orderMapper = transaction.getOrderMapper();
            return orderDao.findAll().stream()
                    .map(orderMapper::mapFrom)
                    .collect(toList());
        } catch (Exception e) {
            log.error("Failure to find all orders", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OrderDto> findAllOrders(Integer page, Integer limit) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var orderDao = transaction.getOrderDao();
            var orderMapper = transaction.getOrderMapper();
            return orderDao.findAllWithLimit(page, limit).stream()
                    .map(orderMapper::mapFrom)
                    .collect(toList());
        } catch (Exception e) {
            log.error("Failure to find all orders with limit", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OrderDto> findAllOrdersByClientId(Integer clientId) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var orderDao = transaction.getOrderDao();
            var orderMapper = transaction.getOrderMapper();
            return orderDao.findAllOrdersByClientId(clientId).stream()
                    .map(orderMapper::mapFrom)
                    .collect(toList());
        } catch (Exception e) {
            log.error("Failure to find all orders by client ID", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<OrderDto> findOrderById(Long id) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var orderDao = transaction.getOrderDao();
            var orderMapper = transaction.getOrderMapper();
            return orderDao.findById(id)
                    .map(orderMapper::mapFrom);
        } catch (Exception e) {
            log.error("Failure to find the order by ID", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<OrderDto> findOrderIfBookIsLoaned(Integer bookId) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var orderDao = transaction.getOrderDao();
            var orderMapper = transaction.getOrderMapper();
            return orderDao.findIfLoaned(bookId)
                    .map(orderMapper::mapFrom);
        } catch (Exception e) {
            log.error("Failure to find if the order is loaned", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Order makeOrder(OrderCreationDto orderCreationDto) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var orderDao = transaction.getOrderDao();
            var orderCreationMapper = transaction.getOrderCreationMapper();
            var order = orderDao.create(orderCreationMapper.mapFrom(orderCreationDto));
            var createdOrder = orderDao.findById(order.getId()).orElse(null);
            transaction.commit();
            log.info("New order was made: {}", createdOrder);
            return order;
        } catch (Exception e) {
            log.error("Failure to make order", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateOrder(OrderCreationDto orderCreationDto) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var orderDao = transaction.getOrderDao();
            var orderCreationMapper = transaction.getOrderCreationMapper();
            orderDao.update(orderCreationMapper.mapFrom(orderCreationDto));
            var updatedOrder = orderDao.findById(orderCreationDto.getId());
            transaction.commit();
            log.info("The order with id[{}] was updated, current order: {}", orderCreationDto.getId(), updatedOrder);
        } catch (Exception e) {
            log.error("Failure to update the order", e);
            throw new ServiceException(e);
        }
    }

    public static OrderServiceImpl getInstance() {
        return INSTANCE;
    }
}
