package com.zinko.dao;

import com.zinko.model.Book;

import java.util.List;

public interface BookDao {

    void creatBook(Book book);

    Book findBookById(int id);

    List<Book> findAllBook();

    Book findBookByIsbn(String isbn);

    Book findBookByAuthor(String author);

    boolean updateBook(Book book);

    boolean deleteBook(int id);

    List<Book> findByAuthor(String author);

    Long countAll();

    void updateRS(Book book);

    void createRs(Book book);

    void printTableInfo();

}
