package com.mathewgv.library.dao.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.GenreDao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.Genre;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Slf4j
public class GenreDaoImpl extends DaoConnection implements GenreDao {

    private static final GenreDaoImpl INSTANCE = new GenreDaoImpl();

    private static final String ID = "id";
    private static final String TITLE = "title";

    private static final String CREATE_SQL = """
            INSERT INTO genres (title)
            VALUES (?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM genres
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE genres
                SET title = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT genres.id, genres.title
            FROM genres
                JOIN book_metas_genres bmg ON genres.id = bmg.genre_id
                JOIN book_metas bm ON bm.id = bmg.book_meta_id
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String FIND_GENRES_OF_THE_BOOK_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE bm.id = ?;
            """;

    private static final String FIND_BY_TITLE_SQL = FIND_ALL_SQL + """
            WHERE genres.title = ?;
            """;

    private GenreDaoImpl() {
    }

    @Override
    public Optional<Genre> findByTitle(String title) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_BY_TITLE_SQL)) {
            preparedStatement.setObject(1, title);
            var resultSet = preparedStatement.executeQuery();
            Genre genre = null;
            if (resultSet.next()) {
                genre = buildGenre(resultSet);
            }
            return Optional.ofNullable(genre);
        } catch (SQLException e) {
            log.error("Error occurred while searching a genre by title", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Genre> findAllGenresOfTheBook(Integer bookMetaId) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_GENRES_OF_THE_BOOK_BY_ID_SQL)) {
            preparedStatement.setObject(1, bookMetaId);
            var resultSet = preparedStatement.executeQuery();
            List<Genre> genres = new ArrayList<>();
            while (resultSet.next()) {
                genres.add(buildGenre(resultSet));
            }
            return genres;
        } catch (SQLException e) {
            log.error("Error occurred while searching genres of the book", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Genre create(Genre entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(CREATE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getTitle());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(ID));
            }
            return entity;
        } catch (SQLException e) {
            log.error("Error occurred while creating the genre", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Genre> findAll() throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Genre> genres = new ArrayList<>();
            while (resultSet.next()) {
                genres.add(buildGenre(resultSet));
            }
            return genres;
        } catch (SQLException e) {
            log.error("Error occurred while searching all genres", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Genre> findById(Integer id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();
            Genre genre = null;
            if (resultSet.next()) {
                genre = buildGenre(resultSet);
            }
            return Optional.ofNullable(genre);
        } catch (SQLException e) {
            log.error("Error occurred while searching the genre by ID", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Genre entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getTitle());
            preparedStatement.setObject(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error occurred while updating the genre", e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Error occurred while deleting the genre", e);
            throw new DaoException(e);
        }
    }

    private Genre buildGenre(ResultSet resultSet) throws SQLException {
        return new Genre(
                resultSet.getInt(ID),
                resultSet.getString(TITLE)
        );
    }

    public static GenreDaoImpl getInstance() {
        return INSTANCE;
    }
}
