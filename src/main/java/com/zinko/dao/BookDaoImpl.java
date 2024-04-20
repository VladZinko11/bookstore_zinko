package com.zinko.dao;

import com.zinko.model.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
            statement.setDate(4, Date.valueOf(book.getPublicationDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Book findBookById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, author, title, isbn, publication_date FROM book WHERE id=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Book book = new Book();
            book.setId(resultSet.getLong(1));
            book.setAuthor(resultSet.getString(2));
            book.setTitle(resultSet.getString(3));
            book.setIsbn(resultSet.getString(4));
            if(resultSet.getDate(5)!=null) {
                book.setPublicationDate(resultSet.getDate(5).toLocalDate());
            } else book.setPublicationDate(null);
            return book;
        }
        catch (SQLException e) {
            e.printStackTrace();
        } throw new RuntimeException();
    }

    @Override
    public List<Book> findAllBook() {
        List<Book>list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, author, title, isbn, publication_date FROM book");
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong(1));
                book.setAuthor(resultSet.getString(2));
                book.setTitle(resultSet.getString(3));
                book.setIsbn(resultSet.getString(4));
                if(resultSet.getDate(5)!=null) {
                    book.setPublicationDate(resultSet.getDate(5).toLocalDate());
                } else book.setPublicationDate(null);
                list.add(book);
            }
            return list;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
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
