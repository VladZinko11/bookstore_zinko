package com.zinko.service;

import com.zinko.dao.BookDao;
import com.zinko.dao.BookDaoImpl;
import com.zinko.model.Book;

import java.time.LocalDate;

public class BookService {
    BookDaoImpl bookDao = new BookDaoImpl();

    public void findAllBook() {
        bookDao.findAllBook().forEach(System.out::println);
    }

    public void findBookById(Long id) {
        System.out.println(bookDao.findBookById(id));
    }

    public void deleteBook(Long id) {
        System.out.println(bookDao.deleteBook(id));
    }

    public void createBook(String author, String title, String isbn, int year) {
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPublicationDate(LocalDate.of(year, 1, 1));
        bookDao.creatBook(book);
    }
}
