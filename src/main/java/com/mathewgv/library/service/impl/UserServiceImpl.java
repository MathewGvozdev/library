package com.mathewgv.library.service.impl;

import com.mathewgv.library.dao.transaction.TransactionFactory;
import com.mathewgv.library.dao.user.UserDao;
import com.mathewgv.library.dao.user.UserInfoDao;
import com.mathewgv.library.entity.user.UserInfo;
import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.service.dto.UserDto;
import com.mathewgv.library.service.mapper.factory.MapperFactory;
import com.mathewgv.library.service.UserService;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.mapper.impl.UserRegistrationMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
public class UserServiceImpl implements UserService {

    private static final UserServiceImpl INSTANCE = new UserServiceImpl();

    private final TransactionFactory transactionFactory = TransactionFactory.getInstance();
    private final MapperFactory mapperFactory = MapperFactory.getInstance();

    private UserServiceImpl() {
    }

    @Override
    public List<UserDto> findAllUsers(Integer page, Integer limit) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var userInfoDao = transaction.getUserInfoDao();
            var userMapper = transaction.getUserMapper();
            return userInfoDao.findAll(page, limit).stream()
                    .map(userMapper::mapFrom)
                    .collect(toList());
        } catch (Exception e) {
            log.error("Failure to find all books", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateUserInfo(UserCreationDto userCreationDto) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var userInfoDao = transaction.getUserInfoDao();
            var userDao = transaction.getUserDao();
            var userRegistrationMapper = transaction.getUserRegistrationMapper();
            var userInfo = userRegistrationMapper.mapFrom(userCreationDto);
            userInfoDao.update(userInfo);
            userDao.update(userInfo.getUser());
            var updatedUser = userInfoDao.findInfoByUserId(userCreationDto.getId());
            transaction.commit();
            log.info("The user with id[{}] was updated, current user: {}", userCreationDto.getId(), updatedUser);
        } catch (Exception e) {
            log.error("Failure to update the order", e);
            throw new ServiceException(e);
        }
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
        try (var transaction = transactionFactory.getTransaction()) {
            var userInfoDao = transaction.getUserInfoDao();
            var userMapper = transaction.getUserMapper();
            return userInfoDao.findInfoByUserId(id)
                    .map(userMapper::mapFrom);
        } catch (Exception e) {
            log.error("Failure to register new user", e);
            throw new ServiceException(e);
        }
    }

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }
}
