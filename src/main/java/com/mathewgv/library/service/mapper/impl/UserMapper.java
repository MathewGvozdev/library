package com.mathewgv.library.service.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.service.dto.UserDto;
import com.mathewgv.library.entity.UserInfo;
import com.mathewgv.library.service.mapper.Mapper;

public class UserMapper extends DaoConnection implements Mapper<UserInfo, UserDto> {

    private static final UserMapper INSTANCE = new UserMapper();

    private UserMapper(){
    }

    @Override
    public UserDto mapFrom(UserInfo object) {
        return UserDto.builder()
                .id(object.getId())
                .login(object.getUser().getLogin())
                .firstName(object.getFirstName())
                .surname(object.getSurname())
                .telephone(object.getTelephone())
                .passportNumber(object.getPassportNumber())
                .role(object.getUser().getRole().getTitle())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
