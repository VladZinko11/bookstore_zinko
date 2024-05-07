package com.zinko.service.impl;

import com.zinko.data.dao.BookDao;
import com.zinko.data.dao.entity.Book;
import com.zinko.service.BookService;
import com.zinko.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    final BookDao bookDao;

    public BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setAuthor(book.getAuthor());
        bookDto.setTitle(book.getTitle());
        bookDto.setId(book.getId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setPublicationDate(book.getPublicationDate());
        return bookDto;
    }

    public Book toBook(BookDto bookDto) {
        Book book = new Book();
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setId(bookDto.getId());
        book.setIsbn(bookDto.getIsbn());
        book.setPublicationDate(bookDto.getPublicationDate());
        return book;
    }

    @Override
    public BookDto findById(Long id) {
        log.debug("BookService method findById call with id: {}", id);
        Book book = bookDao.findBookById(id);
        if(book!=null) return toDto(bookDao.findBookById(id));
        else throw new RuntimeException("Not found book with id: " + id);
    }

    @Override
    public List<BookDto> findAll() {
        log.debug("BookService method findAll call");
        return bookDao.findAllBook().stream().map(this::toDto).toList();
    }

    @Override
    public BookDto create(BookDto bookDto) {
        log.debug("BookService method create {}", bookDto);
        Book book = bookDao.creatBook(toBook(bookDto));
        if(book!=null) return toDto(book);
        else throw new RuntimeException("Book with isbn: " + bookDto.getIsbn() + " is exist");
    }

    @Override
    public BookDto update(BookDto bookDto) {
        log.debug("BookService method update call {}", bookDto);
        BookDto bookBefore = findById(bookDto.getId());
        if(bookDto.getTitle()==null) bookDto.setTitle(bookBefore.getTitle());
        if(bookDto.getAuthor()==null) bookDto.setAuthor(bookBefore.getAuthor());
        if(bookDto.getIsbn()==null) bookDto.setIsbn(bookBefore.getIsbn());
        if(bookDto.getPublicationDate()==null) bookDto.setPublicationDate(bookBefore.getPublicationDate());
        Book newBook = bookDao.updateBook(toBook(bookDto));
        return toDto(newBook);
    }

    @Override
    public void delete(Long id) {
        log.debug("BookService method delete call with id: {}", id);
        if(!bookDao.deleteBook(id)) throw new RuntimeException("Not found book with id: " + id);
    }
}
