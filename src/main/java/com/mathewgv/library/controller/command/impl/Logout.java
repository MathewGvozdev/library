package com.mathewgv.library.controller.command.impl;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.UrlPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logout implements Command {

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getSession().invalidate();
            return new Router(req.getContextPath() + UrlPath.HOME, RoutingType.REDIRECT);
        } catch (ServiceException e) {
            log.error("Failure to logout", e);
            req.setAttribute(AttributeName.ERROR, "Error in logout");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
