package com.zinko.data.dao.impl;

import com.zinko.data.dao.UserDao;
import com.zinko.data.dao.connection.ConnectionContext;
import com.zinko.data.dao.entity.User;
import com.zinko.data.dao.entity.enums.Role;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserDaoImpl implements UserDao {
    public static final int PARAMETER_INDEX_1 = 1;
    public static final int PARAMETER_INDEX_2 = 2;
    public static final int PARAMETER_INDEX_3 = 3;
    public static final int PARAMETER_INDEX_4 = 4;
    public static final int PARAMETER_INDEX_5 = 5;
    public static final int COLUMN_INDEX_1 = 1;
    public static final int COLUMN_INDEX_2 = 2;
    public static final int COLUMN_INDEX_3 = 3;
    public static final int COLUMN_INDEX_4 = 4;
    public static final int COLUMN_INDEX_5 = 5;
    public static final int COLUMN_INDEX_6 = 6;
    public static final int PARAMETER_INDEX_6 = 6;
    public static final String SELECT_COUNT = "SELECT COUNT(*) FROM public.user";
    public static final String SELECT_BY_LAST_NAME = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, e.role FROM public.user AS u JOIN public.enum_role AS e ON u.id_enum_role=e.id WHERE u.last_name=?";
    public static final String SELECT_BY_EMAIL = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, e.role FROM public.user AS u JOIN public.enum_role AS e ON u.id_enum_role=e.id WHERE u.email=?";
    public static final String DELETE = "DELETE FROM public.user WHERE id=?";
    public static final String UPDATE = "UPDATE public.user SET first_name=?, last_name=?, email=?, password=?, id_enum_role=? WHERE id=?";
    public static final String SELECT_ALL = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, e.role FROM public.user AS u JOIN public.enum_role AS e ON u.id_enum_role=e.id ORDER BY u.id";
    public static final String SELECT_BY_ID = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, e.role FROM public.user AS u JOIN public.enum_role AS e ON u.id_enum_role=e.id WHERE u.id=?";
    public static final String CREATE = "INSERT INTO public.user (first_name, last_name, email, password, id_enum_role) " + "VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ID_ROLE = "SELECT id FROM enum_role WHERE role=?";

    private static User getUserFromResulSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(COLUMN_INDEX_1));
        user.setFirstName(resultSet.getString(COLUMN_INDEX_2));
        user.setLastName(resultSet.getString(COLUMN_INDEX_3));
        user.setEmail(resultSet.getString(COLUMN_INDEX_4));
        user.setPassword(resultSet.getString(COLUMN_INDEX_5));
        user.setRole(Role.valueOf(resultSet.getString(COLUMN_INDEX_6)));
        return user;
    }

    private static Long getIdRole(User user, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_ID_ROLE);
        statement.setString(PARAMETER_INDEX_1, user.getRole().toString());
        ResultSet resultSet = statement.executeQuery();
        log.debug("a database access occurred");
        resultSet.next();
        return resultSet.getLong(COLUMN_INDEX_1);
    }

    @Override
    public User create(User user) {
        try (Connection connection = ConnectionContext.getConnection()) {
            log.debug("a database access occurred");
            if (findByEmail(user.getEmail()) == null) {
                PreparedStatement statement = connection.prepareStatement(CREATE);
                statement.setString(PARAMETER_INDEX_1, user.getFirstName());
                statement.setString(PARAMETER_INDEX_2, user.getLastName());
                statement.setString(PARAMETER_INDEX_3, user.getEmail());
                statement.setString(PARAMETER_INDEX_4, user.getPassword());
                statement.setLong(PARAMETER_INDEX_5, getIdRole(user, connection));
                statement.executeUpdate();
                log.debug("a database access occurred");
                return findByEmail(user.getEmail());
            } else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = ConnectionContext.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(PARAMETER_INDEX_1, id);
            ResultSet resultSet = statement.executeQuery();
            log.debug("a database access occurred");
            if (resultSet.next()) return getUserFromResulSet(resultSet);
            else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<User> findAll() {
        try (Connection connection = ConnectionContext.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            log.debug("a database access occurred");
            List<User> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(getUserFromResulSet(resultSet));
            }
            if(list.isEmpty()) return null;
            else return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User update(User user) {
        try (Connection connection = ConnectionContext.getConnection()) {
            log.debug("a database access occurred");
            if (findById(user.getId()) != null) {
                PreparedStatement statement = connection.prepareStatement(UPDATE);
                statement.setString(PARAMETER_INDEX_1, user.getFirstName());
                statement.setString(PARAMETER_INDEX_2, user.getLastName());
                statement.setString(PARAMETER_INDEX_3, user.getEmail());
                statement.setString(PARAMETER_INDEX_4, user.getPassword());
                statement.setLong(PARAMETER_INDEX_5, getIdRole(user, connection));
                statement.setLong(PARAMETER_INDEX_6, user.getId());
                statement.executeUpdate();
                log.debug("a database access occurred");
                return findById(user.getId());
            } else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(User user) {
        try (Connection connection = ConnectionContext.getConnection()) {
            log.debug("a database access occurred");
            if (findById(user.getId()) != null) {
                PreparedStatement statement = connection.prepareStatement(DELETE);
                statement.setLong(PARAMETER_INDEX_1, user.getId());
                statement.executeUpdate();
                log.debug("a database access occurred");
                return true;
            } else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByEmail(String email) {
        try (Connection connection = ConnectionContext.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_EMAIL);
            statement.setString(PARAMETER_INDEX_1, email);
            ResultSet resultSet = statement.executeQuery();
            log.debug("a database access occurred");
            if (resultSet.next()) return getUserFromResulSet(resultSet);
            else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findByLastName(String lastName) {
        List<User> list = new ArrayList<>();
        try (Connection connection = ConnectionContext.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_LAST_NAME);
            statement.setString(PARAMETER_INDEX_1, lastName);
            ResultSet resultSet = statement.executeQuery();
            log.debug("a database access occurred");
            while (resultSet.next()) {
                list.add(getUserFromResulSet(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long countAll() {
        try (Connection connection = ConnectionContext.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_COUNT);
            log.debug("a database access occurred");
            return resultSet.getLong(COLUMN_INDEX_1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
