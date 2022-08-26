package com.mathewgv.library.dao.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.AuthorDao;
import com.mathewgv.library.dao.BookMetaDao;
import com.mathewgv.library.dao.GenreDao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.dao.filter.BookFilter;
import com.mathewgv.library.dao.filter.SelectFilter;
import com.mathewgv.library.dao.filter.SortType;
import com.mathewgv.library.entity.Author;
import com.mathewgv.library.entity.BookMeta;
import com.mathewgv.library.entity.Genre;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Slf4j
public class BookMetaDaoImpl extends DaoConnection implements BookMetaDao {

    private static final BookMetaDaoImpl INSTANCE = new BookMetaDaoImpl();

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String SERIES = "series";
    private static final String IMAGE = "image";

    private static final String SAVE_SQL = """
            INSERT INTO book_metas (title, series, image)
            VALUES (?, ?, ?)
            """;

    private static final String SAVE_AUTHORS_AND_BOOKS_SQL = """
            INSERT INTO book_metas_authors (book_meta_id, author_id)
            VALUES (?, ?)
            """;

    private static final String SAVE_BOOKS_AND_GENRES_SQL = """
            INSERT INTO book_metas_genres (book_meta_id, genre_id)
            VALUES (?, ?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM book_metas
            WHERE id = ?
            """;

    private static final String DELETE_AUTHORS_AND_BOOKS_SQL = """
            DELETE FROM book_metas_authors
            WHERE book_meta_id = ?
            """;

    private static final String DELETE_BOOKS_AND_GENRES_SQL = """
            DELETE FROM book_metas_genres
            WHERE book_meta_id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE book_metas
                SET title = ?,
                series = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, title, series, image
            FROM book_metas
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String FIND_BY_TITLE_SQL = FIND_ALL_SQL + """
            WHERE title = ?
            """;

    private static final String FIND_ALL_BY_FILTER_SQL = """
            SELECT bm.id,
                   bm.title,
                   bm.series,
                   bm.image,
                   string_agg(g.title, ', '),
                   author_req.author
            FROM book_metas bm
                     JOIN book_metas_genres bmg on bm.id = bmg.book_meta_id
                     JOIN genres g on bmg.genre_id = g.id
                     JOIN (SELECT bm.id, bm.title, bm.series, string_agg(concat(a.first_name, ' ', a.surname), ', ') author
                           FROM book_metas bm
                                    JOIN book_metas_authors abm ON bm.id = abm.book_meta_id
                                    JOIN authors a ON a.id = abm.author_id
                           GROUP BY bm.id, bm.title, bm.series) author_req ON author_req.id = bm.id
            GROUP BY bm.id, bm.title, bm.series, author_req.author, bm.image
            """;

    private final AuthorDao authorDao = AuthorDaoImpl.getInstance();
    private final GenreDao genreDao = GenreDaoImpl.getInstance();

    private BookMetaDaoImpl() {
    }


    @Override
    public List<BookMeta> findAllByFilter(BookFilter filter) throws DaoException {
        var filterSqlRequest = filter.getSqlRequest(FIND_ALL_BY_FILTER_SQL, SortType.ID);
        try (var preparedStatement = connection.get().prepareStatement(filterSqlRequest)) {
            for (int i = 0; i < filter.getConditionsSize(); i++) {
                preparedStatement.setObject(i + 1, filter.getParameterValue(i));
            }
            var resultSet = preparedStatement.executeQuery();
            List<BookMeta> bookMetas = new ArrayList<>();
            while (resultSet.next()) {
                bookMetas.add(buildBookMeta(resultSet));
            }
            return bookMetas;
        } catch (SQLException e) {
            log.error("Error occurred while searching book-metas by filter", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<BookMeta> findAllWithLimit(Integer page, Integer limit) throws DaoException {
        var selectFilter = new SelectFilter(page, limit);
        var filterSqlRequest = selectFilter.getSqlRequest(FIND_ALL_SQL, SortType.ID);
        try (var preparedStatement = connection.get().prepareStatement(filterSqlRequest)) {
            selectFilter.setParamsToQuery(preparedStatement);
            var resultSet = preparedStatement.executeQuery();
            List<BookMeta> bookMetas = new ArrayList<>();
            while (resultSet.next()) {
                bookMetas.add(buildBookMeta(resultSet));
            }
            return bookMetas;
        } catch (SQLException e) {
            log.error("Error occurred while searching all book-metas with limit", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<BookMeta> findByTitle (String title) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_BY_TITLE_SQL)) {
            preparedStatement.setObject(1, title);
            var resultSet = preparedStatement.executeQuery();
            BookMeta bookMeta = null;
            if (resultSet.next()) {
                bookMeta = buildBookMeta(resultSet);
            }
            return Optional.ofNullable(bookMeta);
        } catch (SQLException e) {
            log.error("Error occurred while searching the book-meta by title", e);
            throw new DaoException(e);
        }
    }

    @Override
    public BookMeta create(BookMeta entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS);
             var psForAuthorBookMetaLink = connection.get().prepareStatement(SAVE_AUTHORS_AND_BOOKS_SQL);
             var psForBookMetaGenreLink = connection.get().prepareStatement(SAVE_BOOKS_AND_GENRES_SQL)) {

            preparedStatement.setObject(1, entity.getTitle());
            preparedStatement.setObject(2, entity.getSeries());
            preparedStatement.setObject(3, entity.getImage());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(ID));
            }

            for (Author author : entity.getAuthors()) {
                psForAuthorBookMetaLink.setObject(1, entity.getId());
                psForAuthorBookMetaLink.setObject(2, author.getId());
                psForAuthorBookMetaLink.executeUpdate();
            }

            for (Genre genre : entity.getGenres()) {
                psForBookMetaGenreLink.setObject(1, entity.getId());
                psForBookMetaGenreLink.setObject(2, genre.getId());
                psForBookMetaGenreLink.executeUpdate();
            }

            return entity;
        } catch (SQLException e) {
            log.error("Error occurred while creating the book-meta", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<BookMeta> findAll() throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<BookMeta> bookMetas = new ArrayList<>();
            while (resultSet.next()) {
                bookMetas.add(buildBookMeta(resultSet));
            }
            return bookMetas;
        } catch (SQLException e) {
            log.error("Error occurred while searching all book-metas", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<BookMeta> findById(Integer id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();
            BookMeta bookMeta = null;
            if (resultSet.next()) {
                bookMeta = buildBookMeta(resultSet);
            }
            return Optional.ofNullable(bookMeta);
        } catch (SQLException e) {
            log.error("Error occurred while searching the book-meta by ID", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(BookMeta entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getTitle());
            preparedStatement.setObject(2, entity.getSeries());
            preparedStatement.setObject(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error occurred while updating the book-meta", e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(DELETE_SQL);
             var psForAuthorBookMetaLink = connection.get().prepareStatement(DELETE_AUTHORS_AND_BOOKS_SQL);
             var psForBookMetaGenreLink = connection.get().prepareStatement(DELETE_BOOKS_AND_GENRES_SQL)) {
            preparedStatement.setInt(1, id);
            psForAuthorBookMetaLink.setInt(1, id);
            psForBookMetaGenreLink.setInt(1, id);
            return preparedStatement.executeUpdate() > 0
                   && psForAuthorBookMetaLink.executeUpdate() > 0
                   && psForBookMetaGenreLink.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Error occurred while deleting the book-meta", e);
            throw new DaoException(e);
        }
    }

    private BookMeta buildBookMeta(ResultSet resultSet) throws SQLException {
        return new BookMeta(
                resultSet.getInt(ID),
                resultSet.getString(TITLE),
                resultSet.getString(SERIES),
                resultSet.getString(IMAGE),
                authorDao.findAllAuthorsOfTheBook(resultSet.getInt(ID)),
                genreDao.findAllGenresOfTheBook(resultSet.getInt(ID))
        );
    }

    public static BookMetaDaoImpl getInstance() {
        return INSTANCE;
    }
}
