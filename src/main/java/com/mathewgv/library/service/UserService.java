package com.mathewgv.library.service;

import com.mathewgv.library.service.dto.RoleDto;
import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.service.dto.UserDto;
import com.mathewgv.library.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDto> login(String login, String password) throws ServiceException;

    void register(UserCreationDto userDto) throws ServiceException;

    List<UserDto> findAllUsers(Integer page, Integer limit) throws ServiceException;

    Optional<UserDto> findUserInfoById(Integer id) throws ServiceException;

    void updateUserInfo(UserCreationDto userCreationDto) throws ServiceException;

    void updateUserRole(UserCreationDto userCreationDto) throws ServiceException;

    List<RoleDto> findAllRoles() throws ServiceException;
}
