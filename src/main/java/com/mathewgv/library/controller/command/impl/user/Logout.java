package com.mathewgv.library.controller.command.impl.user;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Logout implements Command {

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getSession().invalidate();
            return new Router(req.getContextPath() + "/home", RoutingType.REDIRECT);
        } catch (ServiceException e) {
            log.error("Failure to logout", e);
            req.setAttribute(AttributeName.ERROR, "Error in login");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
