package com.mathewgv.library.service.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.factory.DaoFactory;
import com.mathewgv.library.dao.user.impl.RoleDaoImpl;
import com.mathewgv.library.dao.user.impl.UserDaoImpl;
import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.entity.user.User;
import com.mathewgv.library.entity.user.UserInfo;
import com.mathewgv.library.service.mapper.Mapper;

public class UserRegistrationMapper extends DaoConnection implements Mapper<UserCreationDto, UserInfo> {

    private static final UserRegistrationMapper INSTANCE = new UserRegistrationMapper();

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private static final Integer USER_ROLE_ID = 3;

    private UserRegistrationMapper() {
    }

    @Override
    public UserInfo mapFrom(UserCreationDto object) {
        setConnectionForDependencies();
        var user = daoFactory.getUserDao().findById(object.getId());
        if (user.isPresent()) {
            user.get().setPassword(object.getPassword());
            return new UserInfo(
                    object.getId(),
                    user.get(),
                    object.getFirstName(),
                    object.getSurname(),
                    object.getTelephone(),
                    object.getPassportNumber()
            );
        } else {
            return new UserInfo(
                    object.getId(),
                   new User(object.getId(),
                           object.getLogin(),
                           object.getPassword(),
                           daoFactory.getRoleDao().findById(USER_ROLE_ID).orElse(null)),
                    object.getFirstName(),
                    object.getSurname(),
                    object.getTelephone(),
                    object.getPassportNumber()
            );
        }
    }

    private void setConnectionForDependencies() {
        RoleDaoImpl.getInstance().setConnection(connection.get());
        UserDaoImpl.getInstance().setConnection(connection.get());
    }

    public static UserRegistrationMapper getInstance() {
        return INSTANCE;
    }
}