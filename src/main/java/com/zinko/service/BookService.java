package com.zinko.service;

import com.zinko.service.dto.BookDto;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

    BookDto findById(Long id);

    List<BookDto> findAll();

    BookDto create(String author, String title, String isbn, int year);

    boolean update(String author, String title, String isbn, LocalDate publicationDate);

    boolean delete(Long id);





}
