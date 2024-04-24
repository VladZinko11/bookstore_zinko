package com.zinko.data.dao;

import com.zinko.data.dao.entity.Book;

import java.util.List;

public interface BookDao {

    void creatBook(Book book);

    Book findBookById(Long id);

    List<Book> findAllBook();

    Book findBookByIsbn(String isbn);

    boolean updateBook(Book book);

    boolean deleteBook(Long id);

    List<Book> findByAuthor(String author);

    Long countAll();

    void updateRS(Book book);

    void createRs(Book book);

    void printTableInfo();

}
