package com.zinko.controller;

import com.zinko.service.BookService;
import com.zinko.service.dto.BookDto;
import com.zinko.service.impl.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private final BookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        BookDto bookDto = bookService.findById(id);
        req.setAttribute("book", bookDto);
        req.getRequestDispatcher("jsp/book.jsp").forward(req, resp);
    }
}
