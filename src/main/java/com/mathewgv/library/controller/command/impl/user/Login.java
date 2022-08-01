package com.mathewgv.library.controller.command.impl.user;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.dto.UserDto;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Login implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String CONFIRM = "cfm";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String ROLE = "role";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var userService = serviceFactory.getUserService();
            if (req.getParameter(CONFIRM) == null) {
                return new Router(JspHelper.getPath(JspPath.LOGIN), RoutingType.FORWARD);
            } else {
                return userService.login(req.getParameter(LOGIN), req.getParameter(PASSWORD))
                        .map(dto -> onLoginSuccess(dto, req))
                        .orElseGet(() -> onLoginFail(req));
            }
        } catch (ServiceException e) {
            log.error("Failure to login", e);
            req.setAttribute(AttributeName.ERROR, "Error in login");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private Router onLoginSuccess(UserDto userDto, HttpServletRequest req) {
        req.getSession().setAttribute(USER, userDto);
        req.getSession().setAttribute(ROLE, userDto.getRole());
        return new Router(req.getContextPath() + "/home", RoutingType.REDIRECT);
    }

    private Router onLoginFail(HttpServletRequest req) {
        return new Router(req.getContextPath() + "/home?cmd=login&error", RoutingType.REDIRECT);
    }

}