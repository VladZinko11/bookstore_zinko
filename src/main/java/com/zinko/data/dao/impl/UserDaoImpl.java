package com.zinko.data.dao.impl;

import com.zinko.data.dao.UserDao;
import com.zinko.data.dao.connection.ConnectionContext;
import com.zinko.data.dao.entity.User;
import com.zinko.data.dao.entity.enums.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();
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

    @Override
    public boolean create(User user) {
        try (Connection connection = ConnectionContext.getConnection()) {
            PreparedStatement statement1 = connection.prepareStatement("SELECT id FROM enum_role WHERE role=?");
            statement1.setString(1, user.getRole().toString());
            ResultSet resultSet = statement1.executeQuery();
            resultSet.next();
            Long idRole = resultSet.getLong(1);
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO public.user (first_name, last_name, email, password, id_enum_role) " +
                            "VALUES (?, ?, ?, ?, ?)");
            statement.setString(PARAMETER_INDEX_1, user.getFirstName());
            statement.setString(PARAMETER_INDEX_2, user.getLastName());
            statement.setString(PARAMETER_INDEX_3, user.getEmail());
            statement.setString(PARAMETER_INDEX_4, user.getPassword());
            statement.setLong(PARAMETER_INDEX_5, idRole);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = ConnectionContext.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT u.id, u.first_name, u.last_name, u.email, u.password, e.role FROM public.user AS u JOIN public.enum_role AS e ON u.id_enum_role=e.id WHERE u.id=?");
            statement.setLong(PARAMETER_INDEX_1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) return getUserFromResulSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }



    @Override
    public List<User> findAll() {
        try (Connection connection = ConnectionContext.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT u.id, u.first_name, u.last_name, u.email, u.password, e.role FROM public.user AS u JOIN public.enum_role AS e ON u.id_enum_role=e.id ORDER BY u.id");
            List<User> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(getUserFromResulSet(resultSet));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    @Override
    public boolean update() {

        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public List<User> findByLastName(String LastName) {
        return null;
    }

    @Override
    public Long countAll() {
        return null;
    }
}
