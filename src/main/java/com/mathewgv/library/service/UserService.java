package com.mathewgv.library.service;

import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.service.dto.UserDto;
import com.mathewgv.library.service.exception.ServiceException;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> login(String login, String password) throws ServiceException;

    Integer register(UserCreationDto userDto) throws ServiceException;

    Optional<UserDto> findUserInfoById(Integer id) throws ServiceException;
}
