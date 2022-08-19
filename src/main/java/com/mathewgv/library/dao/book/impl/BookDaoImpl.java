package com.mathewgv.library.dao.book.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.book.BookDao;
import com.mathewgv.library.dao.book.BookMetaDao;
import com.mathewgv.library.dao.book.PublisherDao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.dao.filter.SelectFilter;
import com.mathewgv.library.dao.filter.SortType;
import com.mathewgv.library.entity.book.Book;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Slf4j
public class BookDaoImpl extends DaoConnection implements BookDao {

    private static final BookDaoImpl INSTANCE = new BookDaoImpl();

    private final PublisherDao publisherDao = PublisherDaoImpl.getInstance();
    private final BookMetaDao bookMetaDao = BookMetaDaoImpl.getInstance();

    private static final String ID = "id";
    private static final String PUBLISHER_ID = "publisher_id";
    private static final String BOOK_META_ID = "book_meta_id";
    private static final String PAGES = "pages";
    private static final String PUBLICATION_YEAR = "publication_year";

    private static final String CREATE_SQL = """
            INSERT INTO books (publisher_id, book_meta_id, pages, publication_year)
            VALUES (?, ?, ?, ?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM books
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE books
                SET publisher_id = ?,
                book_meta_id = ?,
                pages = ?,
                publication_year = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, publisher_id, book_meta_id, pages, publication_year
            FROM books
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String FIND_BY_BOOK_META_ID_SQL = """
            SELECT b.id, b.publisher_id, b.book_meta_id, b.pages, b.publication_year
            FROM books b
                LEFT JOIN orders o ON b.id = o.book_id
            WHERE b.book_meta_id = ?
            AND ((fact_date IS NULL AND status IS NULL)
            OR (fact_date IS NOT NULL AND status IS NOT NULL)
            OR (fact_date IS NULL AND status != 'На выдаче'))
            """;

    private BookDaoImpl() {
    }

    @Override
    public List<Book> findAllWithLimit(Integer page, Integer limit) throws DaoException {
        var selectFilter = new SelectFilter(page, limit);
        var filterSqlRequest = selectFilter.getSqlRequest(FIND_ALL_SQL, SortType.ID);
        try (var preparedStatement = connection.get().prepareStatement(filterSqlRequest)) {
            selectFilter.setParamsToQuery(preparedStatement);
            var resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(buildBook(resultSet));
            }
            return books;
        } catch (SQLException e) {
            log.error("Error occurred while searching all books with limit", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Book> findAnyByBookMetaId(Integer bookMetaId) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_BY_BOOK_META_ID_SQL)) {
            preparedStatement.setObject(1, bookMetaId);
            var resultSet = preparedStatement.executeQuery();
            Book book = null;
            if (resultSet.next()) {
                book = buildBook(resultSet);
            }
            return Optional.ofNullable(book);
        } catch (SQLException e) {
            log.error("Error occurred while searching some book by book-meta ID", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Book create(Book entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(CREATE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getPublisher().getId());
            preparedStatement.setObject(2, entity.getBookMeta().getId());
            preparedStatement.setObject(3, entity.getPages());
            preparedStatement.setObject(4, entity.getPublicationYear());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(ID));
            }
            return entity;
        } catch (SQLException e) {
            log.error("Error occurred while creating the book", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Book> findAll() throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(buildBook(resultSet));
            }
            return books;
        } catch (SQLException e) {
            log.error("Error occurred while searching all books", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Book> findById(Integer id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();
            Book book = null;
            if (resultSet.next()) {
                book = buildBook(resultSet);
            }
            return Optional.ofNullable(book);
        } catch (SQLException e) {
            log.error("Error occurred while searching the book by ID", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Book entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getPublisher().getId());
            preparedStatement.setObject(2, entity.getBookMeta().getId());
            preparedStatement.setObject(3, entity.getPages());
            preparedStatement.setObject(4, entity.getPublicationYear());
            preparedStatement.setObject(5, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error occurred while updating the book", e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Error occurred while deleting the book", e);
            throw new DaoException(e);
        }
    }

    private Book buildBook(ResultSet resultSet) throws SQLException {
        return new Book(
                resultSet.getInt(ID),
                publisherDao.findById(resultSet.getInt(PUBLISHER_ID)).orElse(null),
                bookMetaDao.findById(resultSet.getInt(BOOK_META_ID)).orElse(null),
                resultSet.getInt(PAGES),
                resultSet.getInt(PUBLICATION_YEAR)
        );
    }

    public static BookDaoImpl getInstance() {
        return INSTANCE;
    }
}
