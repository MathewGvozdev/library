package com.mathewgv.library.service.impl;

import com.mathewgv.library.dao.filter.SelectFilter;
import com.mathewgv.library.dao.transaction.TransactionFactory;
import com.mathewgv.library.dao.filter.BookFilter;
import com.mathewgv.library.entity.book.*;
import com.mathewgv.library.service.BookService;
import com.mathewgv.library.service.dto.*;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.validation.FilterValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
public class BookServiceImpl implements BookService {

    private static final BookServiceImpl INSTANCE = new BookServiceImpl();

    private final TransactionFactory transactionFactory = TransactionFactory.getInstance();

    @Override
    public Author addAuthor(AuthorDto authorDto) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var authorDao = transaction.getAuthorDao();
            var authorCreationMapper = transaction.getAuthorCreationMapper();
            var author = authorDao.create(authorCreationMapper.mapFrom(authorDto));
            log.info("New author is added to database: {}", author);
            transaction.commit();
            return author;
        } catch (Exception e) {
            log.error("Failure to add a new author", e);
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
            log.info("New book is added to database: {}", book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            log.error("Failure to add a new book", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public BookMeta addBookMeta(BookMetaDto bookMetaDto) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var bookMetaDao = transaction.getBookMetaDao();
            var bookMetaCreationMapper = transaction.getBookMetaCreationMapper();
            var bookMeta = bookMetaDao.create(bookMetaCreationMapper.mapFrom(bookMetaDto));
            log.info("New book-meta is added to database: {}", bookMeta);
            transaction.commit();
            return bookMeta;
        } catch (Exception e) {
            log.error("Failure to add a new book-meta", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Genre addGenre(GenreDto genreDto) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var genreDao = transaction.getGenreDao();
            var genreCreationMapper = transaction.getGenreCreationMapper();
            var genre = genreDao.create(genreCreationMapper.mapFrom(genreDto));
            log.info("New genre is added to database: {}", genre);
            transaction.commit();
            return genre;
        } catch (Exception e) {
            log.error("Failure to add a new genre", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Publisher addPublisher(PublisherDto publisherDto) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var publisherDao = transaction.getPublisherDao();
            var publisherCreationMapper = transaction.getPublisherCreationMapper();
            var publisher = publisherDao.create(publisherCreationMapper.mapFrom(publisherDto));
            log.info("New publisher is added to database: {}", publisher);
            transaction.commit();
            return publisher;
        } catch (Exception e) {
            log.error("Failure to add a new publisher", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookDto> findAllBooks(Integer page, Integer limit) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var bookDao = transaction.getBookDao();
            var bookMapper = transaction.getBookMapper();
            return bookDao.findAll(page, limit).stream()
                    .map(bookMapper::mapFrom)
                    .collect(toList());
        } catch (Exception e) {
            log.error("Failure to find all books", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookMetaDto> findAllBookMetas(Integer page, Integer limit) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var bookDao = transaction.getBookMetaDao();
            var bookMetaMapper = transaction.getBookMetaMapper();
            return bookDao.findAll(page, limit).stream()
                    .map(bookMetaMapper::mapFrom)
                    .collect(toList());
        } catch (Exception e) {
            log.error("Failure to find all book-metas", e);
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
            log.error("Failure to find all book-metas by filter", e);
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
            log.error("Failure to find any book by book-meta ID", e);
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
            log.error("Failure to find book by ID", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteBook(Integer id) throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var bookDao = transaction.getBookDao();
            var isDeleted = bookDao.delete(id);
            var deletedBook = bookDao.findById(id).orElse(null);
            transaction.commit();
            log.info("The book with id[{}] was deleted. Deleted book: {}", id, deletedBook);
            return isDeleted;
        } catch (Exception e) {
            log.error("Failure to delete the book", e);
            throw new ServiceException(e);
        }
    }

    public static BookServiceImpl getInstance() {
        return INSTANCE;
    }
}
