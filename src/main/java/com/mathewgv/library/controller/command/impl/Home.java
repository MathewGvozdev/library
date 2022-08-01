package com.mathewgv.library.controller.command.impl;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Home implements Command {

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        return new Router(JspHelper.getPath(JspPath.HOME), RoutingType.FORWARD);
    }
}
