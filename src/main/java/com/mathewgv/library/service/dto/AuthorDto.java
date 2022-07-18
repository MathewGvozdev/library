package com.mathewgv.library.service.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthorDto {

    String name;
    String surname;
}
