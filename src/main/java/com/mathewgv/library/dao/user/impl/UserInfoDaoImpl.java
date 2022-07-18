package com.mathewgv.library.dao.user.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.user.UserDao;
import com.mathewgv.library.dao.user.UserInfoDao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.user.UserInfo;
import com.mathewgv.library.util.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserInfoDaoImpl extends DaoConnection implements UserInfoDao {

    private static final UserInfoDaoImpl INSTANCE = new UserInfoDaoImpl();

    private final UserDao userDao = UserDaoImpl.getInstance();

    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String FIRST_NAME = "first_name";
    private static final String SURNAME = "surname";
    private static final String TELEPHONE = "telephone";
    private static final String PASSPORT_NUMBER = "passport_number";

    private static final String CREATE_SQL = """
            INSERT INTO users_info (user_id, first_name, surname, telephone, passport_number)
            VALUES (?, ?, ?, ?, ?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM users_info
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE users_info
                SET user_id = ?,
                first_name = ?,
                surname = ?,
                telephone = ?,
                passport_number = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, user_id, first_name, surname, telephone, passport_number
            FROM users_info
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String FIND_PERSONAL_INFO_OF_THE_USER_SQL = """
            SELECT id, user_id, first_name, surname, telephone, passport_number
            FROM users_info
            WHERE user_id = ?
            """;

    private UserInfoDaoImpl() {
    }

    @Override
    public Optional<UserInfo> findInfoByUserId(Integer userId) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_PERSONAL_INFO_OF_THE_USER_SQL)) {
            preparedStatement.setObject(1, userId);
            System.out.println(preparedStatement);
            var resultSet = preparedStatement.executeQuery();
            UserInfo info = null;
            if (resultSet.next()) {
                info = buildUserInfo(resultSet);
            }
            return Optional.ofNullable(info);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public UserInfo create(UserInfo entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(CREATE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getUser().getId());
            preparedStatement.setObject(2, entity.getFirstName());
            preparedStatement.setObject(3, entity.getSurname());
            preparedStatement.setObject(4, entity.getTelephone());
            preparedStatement.setObject(5, entity.getPassportNumber());
            System.out.println(preparedStatement);
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
    public List<UserInfo> findAll() throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<UserInfo> infos = new ArrayList<>();
            while (resultSet.next()) {
                infos.add(buildUserInfo(resultSet));
            }
            return infos;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<UserInfo> findById(Integer id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();
            UserInfo info = null;
            if (resultSet.next()) {
                info = buildUserInfo(resultSet);
            }
            return Optional.ofNullable(info);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(UserInfo entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getUser().getId());
            preparedStatement.setObject(2, entity.getFirstName());
            preparedStatement.setObject(3, entity.getSurname());
            preparedStatement.setObject(4, entity.getTelephone());
            preparedStatement.setObject(5, entity.getPassportNumber());
            preparedStatement.setObject(6, entity.getId());
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

    private UserInfo buildUserInfo(ResultSet resultSet) throws SQLException {
        UserDaoImpl.getInstance().setConnection(connection.get());
        return new UserInfo(
                resultSet.getInt(ID),
                userDao.findById(resultSet.getInt(USER_ID)).orElse(null),
                resultSet.getString(FIRST_NAME),
                resultSet.getString(SURNAME),
                resultSet.getString(TELEPHONE),
                resultSet.getString(PASSPORT_NUMBER)
        );
    }

    public static UserInfoDaoImpl getInstance() {
        return INSTANCE;
    }
}
