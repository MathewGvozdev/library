package com.mathewgv.library.service.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {

    Integer id;
    String login;
    String firstName;
    String surname;
    String telephone;
    String passportNumber;
    String role;
}
