package com.mathewgv.library.controller.command.impl;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.JspHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Home implements Command {

    private static final String JSP_NAME = "index";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
//        req.getParameter("bookId");
        return new Router(JspHelper.getPath(JSP_NAME), RoutingType.FORWARD);
    }
}
