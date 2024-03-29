package com.mathewgv.library.service;

import com.mathewgv.library.service.dto.*;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.validator.ValidationException;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookDto addBook(BookCreationDto bookDto) throws ServiceException, ValidationException;

    List<BookDto> findAllBookMetas() throws ServiceException;

    List<BookDto> findAllBookMetas(Integer page, Integer limit) throws ServiceException;

    List<BookDto> findAllBookMetasByFilter(BookCreationDto dto, Integer page) throws ServiceException, ValidationException;

    List<BookDto> findAllBooks() throws ServiceException;

    List<BookDto> findAllBooks(Integer page, Integer limit) throws ServiceException;

    Optional<BookDto> findAnyBookByBookMetaId(Integer bookMetaId) throws ServiceException;

    Optional<BookDto> findBookById(Integer id) throws ServiceException;

    boolean deleteBook(Integer id) throws ServiceException;
}
