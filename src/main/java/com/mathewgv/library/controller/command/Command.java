package com.mathewgv.library.controller.command;

import com.mathewgv.library.controller.command.router.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {

    Router execute(HttpServletRequest req, HttpServletResponse resp);
}
