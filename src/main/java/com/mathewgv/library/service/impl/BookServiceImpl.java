package com.mathewgv.library.service.impl;

import com.mathewgv.library.dao.book.AuthorDao;
import com.mathewgv.library.dao.book.BookMetaDao;
import com.mathewgv.library.dao.book.GenreDao;
import com.mathewgv.library.dao.transaction.Transaction;
import com.mathewgv.library.dao.transaction.TransactionFactory;
import com.mathewgv.library.dao.filter.BookFilter;
import com.mathewgv.library.entity.book.*;
import com.mathewgv.library.service.BookService;
import com.mathewgv.library.service.dto.*;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.validation.FilterValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
public class BookServiceImpl implements BookService {

    private static final BookServiceImpl INSTANCE = new BookServiceImpl();

    private final TransactionFactory transactionFactory = TransactionFactory.getInstance();

    @Override
    public BookDto addBook(BookDto bookDto) {
        try (var transaction = transactionFactory.getTransaction()) {
            var bookMeta = addBookMetaIfNotExist(transaction, bookDto);
            var publisher = addPublisherIfNotExist(transaction, bookDto);

            var bookDao = transaction.getBookDao();
            var pages = bookDto.getPages();
            var publicationYear = bookDto.getPublicationYear();
            var book = bookDao.create(new Book(publisher, bookMeta, pages, publicationYear));
            var createdBookDto = transaction.getBookMapper().mapFrom(book);
            log.info("New book is added to database: {}", createdBookDto);
            transaction.commit();
            return createdBookDto;
        } catch (Exception e) {
            log.error("Failure to add a new book", e);
            throw new ServiceException(e);
        }
    }

    private Publisher addPublisherIfNotExist(Transaction transaction, BookDto bookDto) {
        var publisherDao = transaction.getPublisherDao();
        var publisherFromDto = bookDto.getPublisher();
        var cityFromDto = bookDto.getPublisherCity();
        var searchedPublisher = publisherDao.findByTitleAndCity(publisherFromDto, cityFromDto);
        Publisher publisher;
        if (searchedPublisher.isEmpty()) {
            publisher = publisherDao.create(new Publisher(publisherFromDto, cityFromDto));
            log.info("New publisher is added to database: {}", publisher);
        } else {
            publisher = searchedPublisher.get();
        }
        return publisher;
    }

    private BookMeta addBookMetaIfNotExist(Transaction transaction, BookDto bookDto) {
        var authors = addAuthorsIfNotExist(transaction, bookDto);
        var genres = addGenresIfNotExist(transaction, bookDto);
        var bookMetaDao = transaction.getBookMetaDao();
        var searchedBookMeta = bookMetaDao.findByTitle(bookDto.getTitle());
        BookMeta bookMeta;
        if (searchedBookMeta.isEmpty()) {
            bookMeta = bookMetaDao.create(new BookMeta(bookDto.getTitle(), bookDto.getSeries(), authors, genres));
            log.info("New bookMeta is added to database: {}", bookMeta);
        } else {
            bookMeta = searchedBookMeta.get();
        }
        return bookMeta;
    }

    private List<Author> addAuthorsIfNotExist(Transaction transaction, BookDto bookDto) {
        var authors = transaction.getAuthorCreationMapper().mapFrom(bookDto);
        var authorDao = transaction.getAuthorDao();
        List<Author> authorList = new ArrayList<>();
        for (Author author : authors) {
            var searchedAuthor = authorDao.findByNameAndSurname(author.getFirstName(), author.getSurname());
            if (searchedAuthor.isEmpty()) {
                var newAuthor = authorDao.create(author);
                authorList.add(newAuthor);
                log.info("New author is added to database: {}", newAuthor);
            } else {
                authorList.add(searchedAuthor.get());
            }
        }
        return authorList;
    }

    private List<Genre> addGenresIfNotExist(Transaction transaction, BookDto bookDto) {
        var genres = transaction.getGenreCreationMapper().mapFrom(bookDto);
        var genreDao = transaction.getGenreDao();
        List<Genre> genreList = new ArrayList<>();
        for (Genre genre : genres) {
            var searchedGenre = genreDao.findByTitle(genre.getTitle());
            if (searchedGenre.isEmpty()) {
                var newGenre = genreDao.create(genre);
                genreList.add(newGenre);
                log.info("New genre is added to database: {}", newGenre);
            } else {
                genreList.add(searchedGenre.get());
            }
        }
        return genreList;
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
            log.error("Failure to find all books", e);
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
    public List<BookDto> findAllBookMetas() throws ServiceException {
        try (var transaction = transactionFactory.getTransaction()) {
            var bookDao = transaction.getBookMetaDao();
            var bookMetaMapper = transaction.getBookMetaMapper();
            return bookDao.findAll().stream()
                    .map(bookMetaMapper::mapFrom)
                    .collect(toList());
        } catch (Exception e) {
            log.error("Failure to find all book-metas", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookDto> findAllBookMetas(Integer page, Integer limit) throws ServiceException {
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
    public List<BookDto> findAllBookMetasByFilter(BookFilter filter) throws ServiceException {
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
            return bookDao.findAnyByBookMetaId(bookMetaId)
                    .map(bookMapper::mapFrom);
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
            return bookDao.findById(id)
                    .map(bookMapper::mapFrom);
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
