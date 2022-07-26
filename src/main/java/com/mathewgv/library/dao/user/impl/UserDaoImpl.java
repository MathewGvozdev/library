package com.mathewgv.library.dao.user.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.user.RoleDao;
import com.mathewgv.library.dao.user.UserDao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.user.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Slf4j
public class UserDaoImpl extends DaoConnection implements UserDao {

    private static final UserDaoImpl INSTANCE = new UserDaoImpl();

    private final RoleDao roleDao = RoleDaoImpl.getInstance();

    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLE_ID = "role_id";

    private static final String CREATE_SQL = """
            INSERT INTO users (login, password, role_id)
            VALUES (?, ?, ?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM users
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE users
                SET login = ?,
                password = ?,
                role_id = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT u.id, login, password, role_id, r.title
            FROM users u
                JOIN roles r on u.role_id = r.id
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE u.id = ?
            """;

    private static final String FIND_BY_EMAIL_AND_PASSWORD_SQL = FIND_ALL_SQL + """
            WHERE login = ? AND password = ?
            """;

    private UserDaoImpl() {
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_BY_EMAIL_AND_PASSWORD_SQL)) {
            preparedStatement.setObject(1, email);
            preparedStatement.setObject(2, password);
            var resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            log.error("Error occurred while searching the user by email and password", e);
            throw new DaoException(e);
        }
    }

    @Override
    public User create(User entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(CREATE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getLogin());
            preparedStatement.setObject(2, entity.getPassword());
            preparedStatement.setObject(3, entity.getRole().getId());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(ID));
            }
            return entity;
        } catch (SQLException e) {
            log.error("Error occurred while creating the user", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            log.error("Error occurred while searching all users", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            log.error("Error occurred while searching the user by ID", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(User entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getLogin());
            preparedStatement.setObject(2, entity.getPassword());
            preparedStatement.setObject(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error occurred while updating the user", e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Error occurred while deleting the user", e);
            throw new DaoException(e);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        RoleDaoImpl.getInstance().setConnection(connection.get());
        return new User(
                resultSet.getInt(ID),
                resultSet.getString(LOGIN),
                resultSet.getString(PASSWORD),
                roleDao.findById(resultSet.getInt(ROLE_ID)).orElse(null)
        );
    }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }
}
