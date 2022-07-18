package com.mathewgv.library.service.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookMetaDto {

    Integer id;
    String title;
    String authors;
    String genres;
    String series;
}
