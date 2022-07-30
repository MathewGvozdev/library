package com.mathewgv.library.service.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookDto {

    Integer id;
//    Integer bookMetaId;
//    Integer publisherId;
    String title;
    String authors;
    String genres;
    String series;
    String publisher;
    String publisherCity;
    Integer pages;
    Integer publicationYear;
}

