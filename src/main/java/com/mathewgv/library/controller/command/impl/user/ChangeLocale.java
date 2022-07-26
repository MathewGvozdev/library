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

public class ChangeLocale implements Command {

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var language = req.getParameter("lang");
            req.getSession().setAttribute("lang", language);
            var prevPage = req.getHeader("referer");

            var page = prevPage != null ? prevPage : "/home";
            return new Router(page, RoutingType.REDIRECT);
        } catch (ServiceException e) {
            req.setAttribute(AttributeName.ERROR, "Error in login");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
