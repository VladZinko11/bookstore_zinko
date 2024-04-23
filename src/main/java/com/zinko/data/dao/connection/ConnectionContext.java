package com.zinko.data.dao.connection;

import javax.management.DescriptorRead;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionContext {
    public static final String POSTGRES_URL = "jdbc:postgresql://localhost:5432/bookstore_bh";
    public static final String POSTGRES_USER = "postgres";
    public static final String POSTGRES_PASSWORD = "root";
    public static final String ELEPHANT_URL = "jdbc:postgresql//flora.db.elephantsql.com:5432/gaemuhia";
    public static final String ELEPHANT_USER = "gaemuhia";
    public static final String  ELEPHANT_PASSWORD = "YcYoJ0CWFoir3Fwh6xX85q_8tVOWm1fA";

    public static final Connection getConnection() throws SQLException {
        return DriverManager.getConnection(POSTGRES_URL, POSTGRES_USER, POSTGRES_PASSWORD);
//        return DriverManager.getConnection(ELEPHANT_URL, ELEPHANT_USER, ELEPHANT_PASSWORD);
    }
}
