package com.zinko.dao;

import com.zinko.data.Book;

import java.util.List;

public interface BookDao {

    void creatBook(Book book);

    Book findBookById(int id);

    List<Book> findAllBook();

    Book findBookByIsbn(String isbn);

    Book findBookByAuthor(String author);

    boolean updateBook(Book book);

    boolean deleteBook(int id);

}
