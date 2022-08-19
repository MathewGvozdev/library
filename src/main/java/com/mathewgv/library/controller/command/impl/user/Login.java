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
import com.mathewgv.library.util.UrlPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Login implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String CONFIRM = "cfm";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private static final String REDIRECT_TO_LOGIN_WITH_ERROR = UrlPath.LOGIN + "&error";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getParameter(CONFIRM) != null) {
                var userService = serviceFactory.getUserService();
                return userService.login(req.getParameter(LOGIN), req.getParameter(PASSWORD))
                        .map(userDto -> onLoginSuccess(userDto, req))
                        .orElseGet(() -> onLoginFail(req));
            }
            return new Router(JspHelper.getPath(JspPath.LOGIN), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to sign in", e);
            req.setAttribute(AttributeName.ERROR, "Error in signing in");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private Router onLoginSuccess(UserDto userDto, HttpServletRequest req) {
        req.getSession().setAttribute(AttributeName.USER, userDto);
        req.getSession().setAttribute(AttributeName.ROLE, userDto.getRole());
        return new Router(req.getContextPath() + UrlPath.HOME, RoutingType.REDIRECT);
    }

    private Router onLoginFail(HttpServletRequest req) {
        return new Router(req.getContextPath() + REDIRECT_TO_LOGIN_WITH_ERROR, RoutingType.REDIRECT);
    }
}
