package com.zinko.service;

import com.zinko.service.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto findById(Long id);

    List<BookDto> findAll();

    BookDto create(String author, String title, String isbn, int year);

    BookDto update();

    boolean delete(Long id);





}
