package com.mathewgv.library.service.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.factory.DaoFactory;
import com.mathewgv.library.dao.user.impl.RoleDaoImpl;
import com.mathewgv.library.dao.user.impl.UserDaoImpl;
import com.mathewgv.library.entity.user.User;
import com.mathewgv.library.entity.user.UserInfo;
import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.service.dto.UserDto;
import com.mathewgv.library.service.mapper.Mapper;

public class UserRoleMapper extends DaoConnection implements Mapper<UserCreationDto, User> {

    private static final UserRoleMapper INSTANCE = new UserRoleMapper();

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private UserRoleMapper(){
    }

    @Override
    public User mapFrom(UserCreationDto object) {
        setConnectionForDependencies();
        return new User(object.getId(),
                daoFactory.getRoleDao().findByTitle(object.getRole()).orElse(null));
    }

    private void setConnectionForDependencies() {
        RoleDaoImpl.getInstance().setConnection(connection.get());
    }

    public static UserRoleMapper getInstance() {
        return INSTANCE;
    }
}
