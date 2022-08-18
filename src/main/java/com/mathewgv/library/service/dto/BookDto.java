package com.mathewgv.library.service.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookDto {

    Integer id;
    String title;
    String authors;
    String genres;
    String series;
    String publisher;
    String publisherCity;
    Integer pages;
    Integer publicationYear;
    String image;
}

