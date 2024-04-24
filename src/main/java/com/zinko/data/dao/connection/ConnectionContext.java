package com.zinko.data.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionContext {
    public static final String POSTGRES_URL = "jdbc:postgresql://localhost:5432/bookstore_bh";
    public static final String POSTGRES_USER = "postgres";
    public static final String POSTGRES_PASSWORD = "root";
    public static final String ELEPHANT_URL = "jdbc:postgresql://surus.db.elephantsql.com:5432/tsojyrly";
    public static final String ELEPHANT_USER = "tsojyrly";
    public static final String  ELEPHANT_PASSWORD = "ib1fM1-UprYuJxNAKvpQNdMwfEb8Z7qn";

    public static final Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(POSTGRES_URL, POSTGRES_USER, POSTGRES_PASSWORD);
        return DriverManager.getConnection(ELEPHANT_URL, ELEPHANT_USER, ELEPHANT_PASSWORD);
    }
}
