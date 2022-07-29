package com.mathewgv.library.service;

import com.mathewgv.library.dao.filter.BookFilter;
import com.mathewgv.library.entity.book.*;
import com.mathewgv.library.service.dto.*;
import com.mathewgv.library.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Author addAuthor(AuthorDto authorDto) throws ServiceException;

    Book addBook(BookDto bookDto) throws ServiceException;

    BookMeta addBookMeta(BookMetaDto bookMetaDto) throws ServiceException;

    Genre addGenre(GenreDto genreDto) throws ServiceException;

    Publisher addPublisher(PublisherDto genreDto) throws ServiceException;

    List<BookDto> findAllBooks(Integer page, Integer limit) throws ServiceException;

    List<BookMetaDto> findAllBookMetas(Integer page, Integer limit) throws ServiceException;

    List<BookMetaDto> findAllBookMetasByFilter(BookFilter filter) throws ServiceException;

    Optional<BookDto> findAnyBookByBookMetaId(Integer bookMetaId) throws ServiceException;

    Optional<BookDto> findBookById(Integer id) throws ServiceException;

    boolean deleteBook(Integer id) throws ServiceException;
}
