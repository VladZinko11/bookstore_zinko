package com.zinko.controller;

import com.zinko.service.BookService;
import com.zinko.service.UserService;
import com.zinko.service.dto.BookDto;
import com.zinko.service.impl.BookServiceImpl;
import com.zinko.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/books")
public class BooksServlet extends HttpServlet {


    private final BookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("books", bookService.findAll());
        req.getRequestDispatcher("jsp/books.jsp").forward(req, resp);
    }
}
