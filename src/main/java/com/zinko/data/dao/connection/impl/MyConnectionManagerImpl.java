package com.zinko.data.dao.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class MyConnectionManagerImpl implements MyConnectionManager {

    private final String url;
    private final String user;
    private final String password;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        }
    }

    public MyConnectionManagerImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public Connection getConnection() {
        try {
            log.info("Connect to local DB");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            log.error("database not found (invalid url or user or password)");
        }
        throw new RuntimeException();
    }
}
