package com.zinko.data.dao.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionContext {
    private static final Logger log = LogManager.getLogger(ConnectionContext.class);
    public static final String PATH_TO_SQL_SCRIPTS = "./src/main/resource/sql";
    public static final String POSTGRES_URL = "jdbc:postgresql://localhost:5432/bookstore_bh";
    public static final String POSTGRES_USER = "postgres";
    public static final String POSTGRES_PASSWORD = "root";
    public static final String ELEPHANT_URL = "jdbc:postgresql://surus.db.elephantsql.com:5432/tsojyrly";
    public static final String ELEPHANT_USER = "tsojyrly";
    public static final String  ELEPHANT_PASSWORD = "ib1fM1-UprYuJxNAKvpQNdMwfEb8Z7qn";

    public static final Connection getConnection(){
        try {
            log.info("Connect to local DB");
            return DriverManager.getConnection(POSTGRES_URL, POSTGRES_USER, POSTGRES_PASSWORD);
//        log.info("Connect to remote DB");
//        return DriverManager.getConnection(ELEPHANT_URL, ELEPHANT_USER, ELEPHANT_PASSWORD);
        }
        catch (SQLException e) {
            log.error("database not found (invalid url or user or password)");
        }
        throw new RuntimeException();
    }

    public static void InitDb() {
        File folder = new File(PATH_TO_SQL_SCRIPTS);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                try {
                    executeScript(PATH_TO_SQL_SCRIPTS + "/" + file.getName());
                } catch (IOException e) {
                    log.error("not found scripts");
                }
            }
        }
    }

    private static void executeScript(String path) throws IOException {
        String script = new String(Files.readAllBytes(Paths.get(path)));
        try (Connection connection = ConnectionContext.getConnection()) {
            Statement statement = connection.createStatement();
            for (String command : script.split(";")) {
                if (!command.trim().isEmpty()) {
                    statement.executeUpdate(command + ";");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
