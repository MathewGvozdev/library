package com.mathewgv.library.service;

import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.service.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> login(String login, String password);

    Integer register(UserCreationDto userDto);

    Optional<UserDto> findUserInfoById(Integer id);
}
