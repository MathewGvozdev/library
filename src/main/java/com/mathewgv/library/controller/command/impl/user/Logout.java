package com.mathewgv.library.controller.command.impl.user;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Logout implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getSession().invalidate();
            return new Router(req.getContextPath() + "/home", RoutingType.REDIRECT);
        } catch (ServiceException e) {
            req.setAttribute(AttributeName.ERROR, "Error in login");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
