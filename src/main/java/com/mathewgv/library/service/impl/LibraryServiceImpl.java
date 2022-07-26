package com.mathewgv.library.service.impl;

import com.mathewgv.library.dao.transaction.TransactionFactory;
import com.mathewgv.library.entity.order.Order;
import com.mathewgv.library.mapper.impl.OrderCreationMapper;
import com.mathewgv.library.service.LibraryService;
import com.mathewgv.library.service.dto.OrderCreationDto;
import com.mathewgv.library.service.dto.OrderDto;
import com.mathewgv.library.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class LibraryServiceImpl implements LibraryService {

    private static final LibraryServiceImpl INSTANCE = new LibraryServiceImpl();

    private final TransactionFactory transactionFactory = TransactionFactory.getInstance();

    @Override
    public void updateOrder(OrderCreationDto orderCreationDto) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var orderDao = transaction.getOrderDao();
            var orderCreationMapper = transaction.getOrderCreationMapper();
            orderDao.update(orderCreationMapper.mapFrom(orderCreationDto));
            transaction.commit();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateStatus(OrderCreationDto orderCreationDto) {
        try (var transaction = transactionFactory.getTransaction()) {
            var orderDao = transaction.getOrderDao();
            var orderCreationMapper = transaction.getOrderCreationMapper();
            orderDao.updateStatus(orderCreationMapper.mapFrom(orderCreationDto));
            transaction.commit();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateWhenBookIsReturned(OrderCreationDto orderCreationDto) {
        try (var transaction = transactionFactory.getTransaction()) {
            var orderDao = transaction.getOrderDao();
            var orderCreationMapper = transaction.getOrderCreationMapper();
            orderDao.updateWhenBookIsReturned(orderCreationMapper.mapFrom(orderCreationDto));
            transaction.commit();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OrderDto> findAllOrdersByClientId(Integer clientId) {
        try (var transaction = transactionFactory.getTransaction()) {
            var orderDao = transaction.getOrderDao();
            var orderMapper = transaction.getOrderMapper();
            return orderDao.findAllOrdersByClientId(clientId).stream()
                    .map(orderMapper::mapFrom)
                    .collect(toList());
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order makeOrder(OrderCreationDto orderCreationDto) {
        try (var transaction = transactionFactory.getTransaction()) {
            var orderDao = transaction.getOrderDao();
            var orderCreationMapper = transaction.getOrderCreationMapper();
            var order = orderDao.create(orderCreationMapper.mapFrom(orderCreationDto));
            transaction.commit();
            return order;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OrderDto> findAllOrders() throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var orderDao = transaction.getOrderDao();
            var orderMapper = transaction.getOrderMapper();
            return orderDao.findAll().stream()
                    .map(orderMapper::mapFrom)
                    .collect(toList());
        } catch (Exception e) {
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
            throw new ServiceException(e);
        }
    }

    public static LibraryServiceImpl getInstance() {
        return INSTANCE;
    }
}
