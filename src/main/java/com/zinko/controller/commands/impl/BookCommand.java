package com.zinko.controller.commands.impl;

import com.zinko.controller.commands.Command;
import com.zinko.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookCommand implements Command {
    private final BookService bookService;
    @Override
    public String execute(HttpServletRequest req) {
        req.setAttribute("book", bookService.findById(Long.valueOf(req.getParameter("id"))));
        return "jsp/book.jsp";
    }
}
