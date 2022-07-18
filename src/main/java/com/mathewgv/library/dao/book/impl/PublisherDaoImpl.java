package com.mathewgv.library.dao.book.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.book.PublisherDao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.book.Publisher;
import com.mathewgv.library.util.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class PublisherDaoImpl extends DaoConnection implements PublisherDao {

    private static final PublisherDaoImpl INSTANCE = new PublisherDaoImpl();

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String CITY = "city";

    private static final String CREATE_SQL = """
            INSERT INTO publishers (title, city)
            VALUES (?, ?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM publishers
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE publishers
                SET title = ?,
                city = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, title, city
            FROM publishers
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private PublisherDaoImpl() {
    }

    @Override
    public Publisher create(Publisher entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(CREATE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getTitle());
            preparedStatement.setObject(2, entity.getCity());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(ID));
            }
            return entity;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Publisher> findAll() throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Publisher> publishers = new ArrayList<>();
            while (resultSet.next()) {
                publishers.add(buildPublisher(resultSet));
            }
            return publishers;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Publisher> findById(Integer id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();
            Publisher publisher = null;
            if (resultSet.next()) {
                publisher = buildPublisher(resultSet);
            }
            return Optional.ofNullable(publisher);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Publisher entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getTitle());
            preparedStatement.setObject(2, entity.getCity());
            preparedStatement.setObject(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Publisher buildPublisher(ResultSet resultSet) throws SQLException {
        return new Publisher(
                resultSet.getInt(ID),
                resultSet.getString(TITLE),
                resultSet.getString(CITY)
        );
    }

    public static PublisherDaoImpl getInstance() {
        return INSTANCE;
    }
}
