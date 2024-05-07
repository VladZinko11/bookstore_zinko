package com.zinko.controller.commands.impl;

import com.zinko.controller.commands.Command;
import com.zinko.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCommand implements Command {
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        req.setAttribute("user", userService.findById(Long.valueOf(req.getParameter("id"))));
        return "jsp/user.jsp";
    }
}
