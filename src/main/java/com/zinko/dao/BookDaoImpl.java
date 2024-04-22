package com.zinko.dao;

import com.zinko.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    public static final String URL = "jdbc:postgresql://localhost:5432/bookstore_bh";
    public static final String USER = "postgres";
    public static final String PASSWORD = "root";
    public static final String SELECT_COUNT = "SELECT COUNT(*) FROM book";
    public static final String SELECT_ALL_BY_AUTHOR = "SELECT id, author, title, isbn, publication_date FROM book WHERE author=?";
    public static final String DELETE = "DELETE FROM book WHERE id=?";
    public static final String SELECT_BY_ID = "SELECT id, author, title, isbn, publication_date FROM book WHERE id=?";
    public static final String INSERT = "INSERT INTO book (author, title, isbn, publication_date) VALUES (?, ?, ?, ?)";
    public static final String SELECT_ALL = "SELECT id, author, title, isbn, publication_date FROM book";
    public static final String SELECT_BY_ISBN = "SELECT id, author, title, isbn, publication_date FROM book WHERE isbn=?";
    public static final String UPDATE = "UPDATE book SET author=?, title=?,publication_date=? WHERE isbn=?";
    public static final int PARAMETER_INDEX_1 = 1;
    public static final int PARAMETER_INDEX_2 = 2;
    public static final int PARAMETER_INDEX_3 = 3;
    public static final int PARAMETER_INDEX_4 = 4;
    public static final int COLUMN_INDEX_1 = 1;
    public static final int COLUMN_INDEX_2 = 2;
    public static final int COLUMN_INDEX_3 = 3;
    public static final int COLUMN_INDEX_4 = 4;
    public static final int COLUMN_INDEX_5 = 5;

    private static Book creatAndInitBookFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong(COLUMN_INDEX_1));
        book.setAuthor(resultSet.getString(COLUMN_INDEX_2));
        book.setTitle(resultSet.getString(COLUMN_INDEX_3));
        book.setIsbn(resultSet.getString(COLUMN_INDEX_4));
        if (resultSet.getDate(COLUMN_INDEX_5) != null) {
            book.setPublicationDate(resultSet.getDate(COLUMN_INDEX_5).toLocalDate());
        } else book.setPublicationDate(null);
        return book;
    }
    
    @Override
    public void creatBook(Book book) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setString(PARAMETER_INDEX_1, book.getAuthor());
            statement.setString(PARAMETER_INDEX_2, book.getTitle());
            statement.setString(PARAMETER_INDEX_3, book.getIsbn());
            statement.setDate(PARAMETER_INDEX_4, Date.valueOf(book.getPublicationDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Book findBookById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(PARAMETER_INDEX_1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            return creatAndInitBookFromResultSet(resultSet);
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    @Override
    public List<Book> findAllBook() {
        List<Book> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                list.add(creatAndInitBookFromResultSet(resultSet));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ISBN);
            statement.setString(PARAMETER_INDEX_1, isbn);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            return creatAndInitBookFromResultSet(resultSet);
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    @Override
    public boolean updateBook(Book book) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ISBN);
            statement.setString(PARAMETER_INDEX_1, book.getIsbn());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                PreparedStatement statement1 = connection.prepareStatement(UPDATE);
                statement1.setString(PARAMETER_INDEX_1, book.getAuthor());
                statement1.setString(PARAMETER_INDEX_2, book.getTitle());
                statement1.setDate(PARAMETER_INDEX_3, Date.valueOf(book.getPublicationDate()));
                statement1.setString(PARAMETER_INDEX_4, book.getIsbn());
                statement1.executeUpdate();
                return true;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    @Override
    public boolean deleteBook(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(PARAMETER_INDEX_1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                PreparedStatement statement1 = connection.prepareStatement(DELETE);
                statement1.setLong(PARAMETER_INDEX_1, id);
                statement1.executeUpdate();
                return true;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_AUTHOR);
            statement.setString(PARAMETER_INDEX_1, author);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(creatAndInitBookFromResultSet(resultSet));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    @Override
    public Long countAll() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_COUNT);
            resultSet.next();
            return resultSet.getLong(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
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
