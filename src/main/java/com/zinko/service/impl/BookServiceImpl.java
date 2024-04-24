package com.zinko.service.impl;

import com.zinko.data.dao.BookDao;
import com.zinko.data.dao.impl.BookDaoImpl;
import com.zinko.data.dao.entity.Book;
import com.zinko.service.BookService;
import com.zinko.service.dto.BookDto;

import java.time.LocalDate;
import java.util.List;

public class BookServiceImpl implements BookService {
    final BookDao bookDao = new BookDaoImpl();

    public BookDto toDo(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setAuthor(book.getAuthor());
        bookDto.setTitle(book.getTitle());
        return bookDto;
    }

    @Override
    public BookDto findById(Long id) {
        return toDo(bookDao.findBookById(id));
    }

    @Override
    public List<BookDto> findAll() {
        return bookDao.findAllBook().stream().map(this::toDo).toList();
    }

    @Override
    public BookDto create(String author, String title, String isbn, int year) {
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPublicationDate(LocalDate.of(year, 1, 1));
        bookDao.creatBook(book);
        return toDo(book);
    }

    @Override
    public boolean update(String author, String title, String isbn, LocalDate publicationDate) {
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPublicationDate(publicationDate);
        return bookDao.updateBook(book);
    }

    @Override
    public boolean delete(Long id) {
        return bookDao.deleteBook(id);
    }
}
