package com.mathewgv.library.service.impl;

import com.mathewgv.library.dao.transaction.TransactionFactory;
import com.mathewgv.library.dao.filter.BookFilter;
import com.mathewgv.library.entity.book.*;
import com.mathewgv.library.service.BookService;
import com.mathewgv.library.service.dto.*;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.validation.FilterValidator;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class BookServiceImpl implements BookService {

    private static final BookServiceImpl INSTANCE = new BookServiceImpl();

    private final TransactionFactory transactionFactory = TransactionFactory.getInstance();

    @Override
    public Author addAuthor(AuthorDto authorDto) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var authorDao = transaction.getAuthorDao();
            var authorCreationMapper = transaction.getAuthorCreationMapper();
            var author = authorDao.create(authorCreationMapper.mapFrom(authorDto));
            transaction.commit();
            return author;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Book addBook(BookDto bookDto) throws ServiceException {
//        Validator.isValid(bookDto);
        try (var transaction = transactionFactory.getTransaction()) {
            var bookDao = transaction.getBookDao();
            var bookCreationMapper = transaction.getBookCreationMapper();
            var book = bookDao.create(bookCreationMapper.mapFrom(bookDto));
            transaction.commit();
            return book;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public BookMeta addBookMeta(BookMetaDto bookMetaDto) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var bookMetaDao = transaction.getBookMetaDao();
            var bookMetaCreationMapper = transaction.getBookMetaCreationMapper();
            var bookMeta = bookMetaDao.create(bookMetaCreationMapper.mapFrom(bookMetaDto));
            transaction.commit();
            return bookMeta;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Genre addGenre(GenreDto genreDto) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var genreDao = transaction.getGenreDao();
            var genreCreationMapper = transaction.getGenreCreationMapper();
            var genre = genreDao.create(genreCreationMapper.mapFrom(genreDto));
            transaction.commit();
            return genre;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Publisher addPublisher(PublisherDto publisherDto) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var publisherDao = transaction.getPublisherDao();
            var publisherCreationMapper = transaction.getPublisherCreationMapper();
            var publisher = publisherDao.create(publisherCreationMapper.mapFrom(publisherDto));
            transaction.commit();
            return publisher;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookDto> findAllBooks() throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var bookDao = transaction.getBookDao();
            var bookMapper = transaction.getBookMapper();
            return bookDao.findAll().stream()
                    .map(bookMapper::mapFrom)
                    .collect(toList());
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookMetaDto> findAllBookMetas() throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var bookDao = transaction.getBookMetaDao();
            var bookMetaMapper = transaction.getBookMetaMapper();
            return bookDao.findAll().stream()
                    .map(bookMetaMapper::mapFrom)
                    .collect(toList());
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookMetaDto> findAllBookMetasByFilter(BookFilter filter) throws ServiceException {
        FilterValidator.validate(filter);
        try (var transaction = transactionFactory.getTransaction()) {
            var bookDao = transaction.getBookMetaDao();
            var bookMetaMapper = transaction.getBookMetaMapper();
            return bookDao.findAllByFilter(filter).stream()
                    .map(bookMetaMapper::mapFrom)
                    .collect(toList());
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<BookDto> findAnyBookByBookMetaId(Integer bookMetaId) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var bookDao = transaction.getBookDao();
            var bookMapper = transaction.getBookMapper();
            return bookDao.findAnyByBookMetaId(bookMetaId).stream()
                    .map(bookMapper::mapFrom)
                    .findFirst();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<BookDto> findBookById(Integer id) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var bookDao = transaction.getBookDao();
            var bookMapper = transaction.getBookMapper();
            return bookDao.findById(id).stream()
                    .map(bookMapper::mapFrom)
                    .findFirst();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateBook(BookDto bookDto) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var bookDao = transaction.getBookDao();
            var bookCreationMapper = transaction.getBookCreationMapper();
            bookDao.update(bookCreationMapper.mapFrom(bookDto));
            transaction.commit();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteBook(Integer id) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var bookDao = transaction.getBookDao();
            var isDeleted = bookDao.delete(id);
            transaction.commit();
            return isDeleted;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public static BookServiceImpl getInstance() {
        return INSTANCE;
    }
}
