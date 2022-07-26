package com.mathewgv.library.service.impl;

import com.mathewgv.library.dao.transaction.TransactionFactory;
import com.mathewgv.library.dao.user.UserDao;
import com.mathewgv.library.dao.user.UserInfoDao;
import com.mathewgv.library.entity.user.User;
import com.mathewgv.library.entity.user.UserInfo;
import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.service.dto.UserDto;
import com.mathewgv.library.mapper.factory.MapperFactory;
import com.mathewgv.library.service.UserService;
import com.mathewgv.library.service.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
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
            log.error("Failure to login", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer register(UserCreationDto userDto) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var userInfo = transaction.getUserRegistrationMapper().mapFrom(userDto);
            var userDao = transaction.getUserDao();
            var userInfoDao = transaction.getUserInfoDao();
            var createdUser = userDao.create(userInfo.getUser());
            var createdUserInfo = userInfoDao.create(userInfo);
            transaction.commit();
            log.info("New user registered. User=[{}], Info=[{}]", createdUser, createdUserInfo);
            return userInfo.getUser().getId();
        } catch (Exception e) {
            log.error("Failure to register new user", e);
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
