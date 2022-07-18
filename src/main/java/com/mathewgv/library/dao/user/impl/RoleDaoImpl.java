package com.mathewgv.library.dao.user.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.user.RoleDao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.user.Role;
import com.mathewgv.library.util.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class RoleDaoImpl extends DaoConnection implements RoleDao {

    private static final RoleDaoImpl INSTANCE = new RoleDaoImpl();

    private static final String ID = "id";
    private static final String TITLE = "title";

    private static final String CREATE_SQL = """
            INSERT INTO roles (title)
            VALUES (?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM roles
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE roles
                SET title = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, title
            FROM roles
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private RoleDaoImpl() {
    }

    @Override
    public Role create(Role entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(CREATE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getTitle());
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
    public List<Role> findAll() throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                roles.add(buildRole(resultSet));
            }
            return roles;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Role> findById(Integer id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();
            Role role = null;
            if (resultSet.next()) {
                role = buildRole(resultSet);
            }
            return Optional.ofNullable(role);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Role entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getTitle());
            preparedStatement.setObject(2, entity.getId());
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

    private Role buildRole(ResultSet resultSet) throws SQLException {
        return new Role(
                resultSet.getInt(ID),
                resultSet.getString(TITLE)
        );
    }

    public static RoleDaoImpl getInstance() {
        return INSTANCE;
    }
}
