package com.mathewgv.library.controller.command.impl;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.service.validator.ValidationException;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import com.mathewgv.library.util.UrlPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Registration implements Command {

    private static final String CONFIRM = "cfm";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "firstName";
    private static final String SURNAME = "surname";
    private static final String TELEPHONE = "telephone";
    private static final String PASSPORT_NUMBER = "passportNumber";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getParameter(CONFIRM) != null) {
                var userService = serviceFactory.getUserService();
                userService.register(buildUserCreationDto(req));
                return new Router(req.getContextPath() + UrlPath.LOGIN, RoutingType.REDIRECT);
            }
            return new Router(JspHelper.getPath(JspPath.REGISTRATION), RoutingType.FORWARD);
        } catch (ValidationException e) {
            req.setAttribute(AttributeName.ERRORS, e.getErrors());
            return new Router(JspHelper.getPath(JspPath.REGISTRATION), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to register a new user", e);
            req.setAttribute(AttributeName.ERROR, "Error in registration");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private UserCreationDto buildUserCreationDto(HttpServletRequest req) {
        return UserCreationDto.builder()
                .login(req.getParameter(LOGIN))
                .password(req.getParameter(PASSWORD))
                .firstName(req.getParameter(FIRST_NAME))
                .surname(req.getParameter(SURNAME))
                .telephone(req.getParameter(TELEPHONE))
                .passportNumber(req.getParameter(PASSPORT_NUMBER))
                .build();
    }
}
