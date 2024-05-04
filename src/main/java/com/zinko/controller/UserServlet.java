package com.zinko.controller;

import com.zinko.data.dao.entity.User;
import com.zinko.service.UserService;
import com.zinko.service.dto.UserDto;
import com.zinko.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.desktop.PreferencesEvent;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        try {
            UserDto user = userService.findById(id);
            req.setAttribute("user", user);
            req.getRequestDispatcher("jsp/user.jsp").forward(req, resp);
        }
        catch (RuntimeException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("jsp/exception.jsp");
        }
    }
}
