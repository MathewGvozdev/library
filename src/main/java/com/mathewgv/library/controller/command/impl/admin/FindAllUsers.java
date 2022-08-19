package com.mathewgv.library.controller.command.impl.admin;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindAllUsers implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String PAGE = "page";

    private static final Integer SHOWED_USERS_LIMIT = 20;

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            var page = Integer.parseInt(req.getParameter(PAGE));
            var userService = serviceFactory.getUserService();
            var showedUsers = userService.findAllUsers(page, SHOWED_USERS_LIMIT);
            req.setAttribute(AttributeName.USERS, showedUsers);
            return new Router(JspHelper.getPath(JspPath.FIND_ALL_USERS), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to find all users", e);
            req.setAttribute(AttributeName.ERROR, "Error in searching users");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        } catch (NumberFormatException e) {
            log.error("Failure to process parameter 'page', it should be a number", e);
            req.setAttribute(AttributeName.ERROR, "Page is not a number");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
