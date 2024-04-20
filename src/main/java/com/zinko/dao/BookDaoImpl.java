package com.zinko.dao;

import com.zinko.model.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class BookDaoImpl implements BookDao {

    public static final String URL = "jdbc:postgresql://localhost:5432/bookstore_bh";
    public static final String USER = "postgres";
    public static final String PASSWORD = "root";

    @Override
    public void creatBook(Book book) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO book (author, title, isbn, publication_date) VALUES (?, ?, ?, ?)");
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getIsbn());
            LocalDate date = book.getPublicationDate();
            if (date != null) {
                statement.setDate(4, new Date(date.getDayOfMonth(), date.getMonthValue(), date.getYear()));
            }else statement.setDate(4, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Book findBookById(int id) {
        return null;
    }

    @Override
    public List<Book> findAllBook() {
        return null;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return null;
    }

    @Override
    public Book findBookByAuthor(String author) {
        return null;
    }

    @Override
    public boolean updateBook(Book book) {
        return false;
    }

    @Override
    public boolean deleteBook(int id) {
        return false;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return null;
    }

    @Override
    public Long countAll() {
        return null;
    }

    @Override
    public void updateRS(Book book) {

    }

    @Override
    public void createRs(Book book) {

    }

    @Override
    public void printTableInfo() {

    }
}
