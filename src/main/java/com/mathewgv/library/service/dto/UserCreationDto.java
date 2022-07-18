package com.mathewgv.library.service.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserCreationDto {

    String login;
    String password;
    String firstName;
    String surname;
    String telephone;
    String passportNumber;
}
