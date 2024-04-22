package com.zinko;


import com.zinko.service.impl.BookServiceImpl;
import com.zinko.data.dao.connection.ConnectionContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Main {
    static BookServiceImpl bookServiceImpl = new BookServiceImpl();

    public static final String PATH_TO_SQL_SCRIPTS = "./src/main/resource/sql";

    public static void main(String[] args) throws IOException {

        InitDb();
        Scanner scanner = new Scanner(System.in);
        boolean execution = true;
        System.out.println("Введите команду: \n     all - для получения каталога всех книг" +
                "\n     get - для получения книги по id \n     delete - для удадения книги по id" +
                "\n     create - для создания и добавления книги \n     exit - для завершения работы с каталогом");
        while (execution) {
            String command = scanner.nextLine();
            switch (command) {
                case "all" -> bookServiceImpl.findAll().forEach(System.out::println);
                case "get" -> {
                    System.out.println("Введите id: ");
                    String id = scanner.nextLine();
                    System.out.println(bookServiceImpl.findById(Long.parseLong(id)));
                }
                case "delete" -> {
                    System.out.println("Введите id: ");
                    String id = scanner.nextLine();
                    System.out.println(bookServiceImpl.delete(Long.parseLong(id)));
                }
                case "create" -> {
                    System.out.println("Введите автора: ");
                    String author = scanner.nextLine();
                    System.out.println("Введите название: ");
                    String title = scanner.nextLine();
                    System.out.println("Введите isbn: ");
                    String isbn = scanner.nextLine();
                    System.out.println("Введите год публикации: ");
                    int date = scanner.nextInt();
                    bookServiceImpl.create(author, title, isbn, date);
                }
                case "exit" -> execution = false;
            }
        }
    }

    private static void InitDb() throws IOException {
        File folder = new File(PATH_TO_SQL_SCRIPTS);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                executeScript(PATH_TO_SQL_SCRIPTS + "/" + file.getName());
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
