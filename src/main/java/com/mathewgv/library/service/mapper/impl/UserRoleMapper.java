package com.mathewgv.library.service.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.factory.DaoFactory;
import com.mathewgv.library.entity.user.User;
import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.service.mapper.Mapper;

public class UserRoleMapper extends DaoConnection implements Mapper<UserCreationDto, User> {

    private static final UserRoleMapper INSTANCE = new UserRoleMapper();

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private UserRoleMapper(){
    }

    @Override
    public User mapFrom(UserCreationDto object) {
        return new User(object.getId(),
                daoFactory.getRoleDao().findByTitle(object.getRole()).orElse(null));
    }

    public static UserRoleMapper getInstance() {
        return INSTANCE;
    }
}
