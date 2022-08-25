package com.mathewgv.library.service.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Setter;
import lombok.Value;

@Value
@Builder
public class BookCreationDto {

    String title;
    String authors;
    String genres;
    String series;
    String publisher;
    String publisherCity;
    Integer pages;
    Integer publicationYear;
    Part image;
}
