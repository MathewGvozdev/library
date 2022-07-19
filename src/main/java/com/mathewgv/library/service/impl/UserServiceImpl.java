package com.mathewgv.library.service.impl;

import com.mathewgv.library.dao.transaction.TransactionFactory;
import com.mathewgv.library.entity.user.UserInfo;
import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.service.dto.UserDto;
import com.mathewgv.library.mapper.factory.MapperFactory;
import com.mathewgv.library.service.UserService;
import com.mathewgv.library.service.exception.ServiceException;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl INSTANCE = new UserServiceImpl();

    private final TransactionFactory transactionFactory = TransactionFactory.getInstance();
    private final MapperFactory mapperFactory = MapperFactory.getInstance();

    private UserServiceImpl() {
    }

    @Override
    public Optional<UserDto> login(String login, String password) throws ServiceException{
        try (var transaction = transactionFactory.getTransaction()) {
            var userDao = transaction.getUserDao();
            var user = userDao.findByEmailAndPassword(login, password);
            var userMapper = mapperFactory.getUserMapper();
            return user.flatMap(userIfPresent -> transaction.getUserInfoDao().findInfoByUserId(userIfPresent.getId()))
                    .map(userMapper::mapFrom);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer register(UserCreationDto userDto) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var userInfo = transaction.getUserRegistrationMapper().mapFrom(userDto);
            transaction.getUserDao().create(userInfo.getUser());
            transaction.getUserInfoDao().create(userInfo);
            transaction.commit();
            return userInfo.getUser().getId();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<UserDto> findUserInfoById(Integer id) throws ServiceException {
//        return daoFactory.getUserInfoDao().findInfoByUserId(id)
//                .map(userMapper::mapFrom);
        return null;
    }

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }
}
