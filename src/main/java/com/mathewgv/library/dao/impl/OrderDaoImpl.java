package com.mathewgv.library.dao.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.BookDao;
import com.mathewgv.library.dao.filter.SelectFilter;
import com.mathewgv.library.dao.filter.SortType;
import com.mathewgv.library.dao.OrderDao;
import com.mathewgv.library.dao.UserDao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.LoanType;
import com.mathewgv.library.entity.Order;
import com.mathewgv.library.entity.OrderStatus;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Slf4j
public class OrderDaoImpl extends DaoConnection implements OrderDao {

    private static final OrderDaoImpl INSTANCE = new OrderDaoImpl();

    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String BOOK_ID = "book_id";
    private static final String ISSUE_DATE = "issue_date";
    private static final String DUE_DATE = "due_date";
    private static final String FACT_DATE = "fact_date";
    private static final String TYPE = "type";
    private static final String STATUS = "status";

    private static final String CREATE_SQL = """
            INSERT INTO orders (user_id,
                                book_id,
                                issue_date,
                                due_date,
                                type,
                                status)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM orders
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE orders
                SET user_id = ?,
                book_id = ?,
                issue_date = ?,
                due_date = ?,
                fact_date = ?,
                type = ?,
                status = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id,
                   user_id,
                   book_id,
                   issue_date,
                   due_date,
                   fact_date,
                   type,
                   status
            FROM orders
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String FIND_IF_LOANED_SQL = FIND_ALL_SQL + """
            WHERE book_id = ?
                AND fact_date IS NULL
                AND (status = 'На выдаче' OR status = 'Просрочен');
            """;

    private static final String FIND_ALL_BY_USER_ID_SQL = FIND_ALL_SQL + """
            WHERE user_id = ?
            """;

    private final UserDao userDao = UserDaoImpl.getInstance();
    private final BookDao bookDao = BookDaoImpl.getInstance();

    private OrderDaoImpl() {
    }

    @Override
    public List<Order> findAllOrdersByClientId(Integer clientId) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_ALL_BY_USER_ID_SQL)) {
            preparedStatement.setObject(1, clientId);
            var resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            log.error("Error occurred while searching the orders by client ID", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> findAllWithLimit(Integer page, Integer limit) throws DaoException {
        var selectFilter = new SelectFilter(page, limit);
        var filterSqlRequest = selectFilter.getSqlRequest(FIND_ALL_SQL, SortType.ID_DESC);
        try (var preparedStatement = connection.get().prepareStatement(filterSqlRequest)) {
            selectFilter.setParamsToQuery(preparedStatement);
            var resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            log.error("Error occurred while searching all orders with limit", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Order> findIfLoaned(Integer bookId) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_IF_LOANED_SQL)) {
            preparedStatement.setObject(1, bookId);
            var resultSet = preparedStatement.executeQuery();
            Order order = null;
            if (resultSet.next()) {
                order = buildOrder(resultSet);
            }
            return Optional.ofNullable(order);
        } catch (SQLException e) {
            log.error("Error occurred while searching the order if it is loaned", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Order create(Order entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(CREATE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getClient().getId());
            preparedStatement.setObject(2, entity.getBook().getId());
            preparedStatement.setObject(3, entity.getIssueDate());
            preparedStatement.setObject(4, entity.getDueDate());
            preparedStatement.setObject(5, entity.getType().getValue());
            preparedStatement.setObject(6, entity.getStatus().getValue());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(ID));
            }
            return entity;
        } catch (SQLException e) {
            log.error("Error occurred while creating the order", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> findAll() throws DaoException {
        var sql = FIND_ALL_SQL + " ORDER BY id DESC";
        try (var preparedStatement = connection.get().prepareStatement(sql)) {
            var resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            log.error("Error occurred while searching all orders", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Order> findById(Long id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();
            Order order = null;
            if (resultSet.next()) {
                order = buildOrder(resultSet);
            }
            return Optional.ofNullable(order);
        } catch (SQLException e) {
            log.error("Error occurred while searching the order by ID", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Order entity) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getClient().getId());
            preparedStatement.setObject(2, entity.getBook().getId());
            preparedStatement.setObject(3, entity.getIssueDate());
            preparedStatement.setObject(4, entity.getDueDate());
            preparedStatement.setObject(5, entity.getFactDate());
            preparedStatement.setObject(6, entity.getType().getValue());
            preparedStatement.setObject(7, entity.getStatus().getValue());
            preparedStatement.setObject(8, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error occurred while updating the order", e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (var preparedStatement = connection.get().prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Error occurred while deleting the order", e);
            throw new DaoException(e);
        }
    }

    private Order buildOrder(ResultSet resultSet) throws SQLException {
        LocalDate factDate = null;
        if (resultSet.getDate(FACT_DATE) != null) {
            factDate = resultSet.getDate(FACT_DATE).toLocalDate();
        }
        return new Order(
                resultSet.getLong(ID),
                userDao.findById(resultSet.getInt(USER_ID)).orElse(null),
                bookDao.findById(resultSet.getInt(BOOK_ID)).orElse(null),
                resultSet.getDate(ISSUE_DATE).toLocalDate(),
                resultSet.getDate(DUE_DATE).toLocalDate(),
                factDate,
                LoanType.findByName(resultSet.getString(TYPE)),
                OrderStatus.findByName(resultSet.getString(STATUS))
        );
    }

    public static OrderDaoImpl getInstance() {
        return INSTANCE;
    }
}
