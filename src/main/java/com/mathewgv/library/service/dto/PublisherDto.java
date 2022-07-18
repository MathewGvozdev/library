package com.mathewgv.library.service.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PublisherDto {

    String title;
    String city;
}
