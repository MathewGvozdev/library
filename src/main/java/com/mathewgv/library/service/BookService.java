package com.mathewgv.library.service;

import com.mathewgv.library.dao.filter.BookFilter;
import com.mathewgv.library.entity.book.*;
import com.mathewgv.library.service.dto.*;
import com.mathewgv.library.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookDto addBook(BookDto bookDto);

    List<BookDto> findAllBooks(Integer page, Integer limit) throws ServiceException;

    List<BookDto> findAllBooks() throws ServiceException;

    List<BookDto> findAllBookMetas(Integer page, Integer limit) throws ServiceException;

    List<BookDto> findAllBookMetas() throws ServiceException;

    List<BookDto> findAllBookMetasByFilter(BookFilter filter) throws ServiceException;

    Optional<BookDto> findAnyBookByBookMetaId(Integer bookMetaId) throws ServiceException;

    Optional<BookDto> findBookById(Integer id) throws ServiceException;

    boolean deleteBook(Integer id) throws ServiceException;
}
