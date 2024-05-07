package com.zinko.controller.commands.impl;

import com.zinko.controller.commands.Command;
import com.zinko.service.BookService;
import com.zinko.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class BooksCommand implements Command {
    private final BookService bookService;

    @Override
    public String execute(HttpServletRequest req) {
        req.setAttribute("books", bookService.findAll());
        return "jsp/books.jsp";
    }
}
