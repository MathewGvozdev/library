package com.mathewgv.library.dao.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.AuthorDao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.Author;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Slf4j
public class AuthorDaoImpl extends DaoConnection implements AuthorDao {

    private static final AuthorDaoImpl INSTANCE = new AuthorDaoImpl();

    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String SURNAME = "surname";

    private static final String CREATE_SQL = """
            INSERT INTO authors (first_name, surname)
            VALUES (?, ?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM authors
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE authors
                SET first_name = ?,
                surname = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT a.id, a.first_name, a.surname
            FROM authors a
                JOIN book_metas_authors bma ON a.id = bma.author_id
                JOIN book_metas bm ON bm.id = bma.book_meta_id
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String FIND_AUTHORS_OF_THE_BOOK_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE bm.id = ?;
            """;

    private static final String FIND_BY_NAME_AND_SURNAME_SQL = FIND_ALL_SQL + """
            WHERE a.first_name = ? AND a.surname = ?;
            """;

    private AuthorDaoImpl() {
    }

    @Override
    public Optional<Author> findByNameAndSurname(String firstName, String surname) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_BY_NAME_AND_SURNAME_SQL)) {
            preparedStatement.setObject(1, firstName);
            preparedStatement.setObject(2, surname);
            var resultSet = preparedStatement.executeQuery();
            Author author = null;
            if (resultSet.next()) {
                author = buildAuthor(resultSet);
            }
            return Optional.ofNullable(author);
        } catch (SQLException e) {
            log.error("Error occurred while searching the author by name and surname", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Author> findAllAuthorsOfTheBook(Integer bookMetaId) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_AUTHORS_OF_THE_BOOK_BY_ID_SQL)) {
            preparedStatement.setObject(1, bookMetaId);
            var resultSet = preparedStatement.executeQuery();
            List<Author> authors = new ArrayList<>();
            while (resultSet.next()) {
                authors.add(buildAuthor(resultSet));
            }
            return authors;
        } catch (SQLException e) {
            log.error("Error occurred while searching authors of the book", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Author create(Author entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(CREATE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getFirstName());
            preparedStatement.setObject(2, entity.getSurname());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(ID));
            }
            return entity;
        } catch (SQLException e) {
            log.error("Error occurred while creating the author", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Author> findAll() throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Author> authors = new ArrayList<>();
            while (resultSet.next()) {
                authors.add(buildAuthor(resultSet));
            }
            return authors;
        } catch (SQLException e) {
            log.error("Error occurred while searching all authors", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Author> findById(Integer id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();
            Author author = null;
            if (resultSet.next()) {
                author = buildAuthor(resultSet);
            }
            return Optional.ofNullable(author);
        } catch (SQLException e) {
            log.error("Error occurred while searching an author by ID", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Author entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getFirstName());
            preparedStatement.setObject(2, entity.getSurname());
            preparedStatement.setObject(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error occurred while updating the author", e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Error occurred while deleting the author", e);
            throw new DaoException(e);
        }
    }

    private Author buildAuthor(ResultSet resultSet) throws SQLException {
        return new Author(
                resultSet.getInt(ID),
                resultSet.getString(FIRST_NAME),
                resultSet.getString(SURNAME)
        );
    }

    public static AuthorDaoImpl getInstance() {
        return INSTANCE;
    }
}
