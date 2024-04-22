package com.zinko;


import com.zinko.dao.BookDaoImpl;
import com.zinko.service.BookService;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Main {
    static BookService bookService = new BookService();

    public static final String PATH_TO_SQL_SCRIPTS = "./src/main/resource/sql";

    public static void main(String[] args) throws IOException {
        InitDb();

        Scanner scanner = new Scanner(System.in);
        boolean execution = true;
        System.out.println("Введите команду: \n     all - для получения каталога всех книг"+
                "\n     get - для получения книги по id \n     delete - для удадения книги по id"+
                "\n     create - для создания и добавления книги \n     exit - для завершения работы с каталогом");
        while (execution) {
            String command = scanner.nextLine();
            switch (command) {
                case "all":
                    bookService.findAllBook();
                    break;
                case "get": {
                    System.out.println("Введите id: ");
                    String id = scanner.nextLine();
                    bookService.findBookById(Long.parseLong(id));
                    break;
                }
                case "delete": {
                    System.out.println("Введите id: ");
                    String id = scanner.nextLine();
                    bookService.deleteBook(Long.parseLong(id));
                    break;
                }
                case "create":
                    System.out.println("Введите автора: ");
                    String author = scanner.nextLine();
                    System.out.println("Введите название: ");
                    String title = scanner.nextLine();
                    System.out.println("Введите isbn: ");
                    String isbn = scanner.nextLine();
                    System.out.println("Введите год публикации: ");
                    int date = scanner.nextInt();
                    bookService.createBook(author, title, isbn, date);
                    break;
                case "exit" :
                    execution=false;
                    break;

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
        try (Connection connection = DriverManager.getConnection(BookDaoImpl.URL, BookDaoImpl.USER, BookDaoImpl.PASSWORD)) {
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
