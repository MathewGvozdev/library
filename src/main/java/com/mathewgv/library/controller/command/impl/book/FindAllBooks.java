package com.mathewgv.library.controller.command.impl.book;

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

public class FindAllBooks implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var allBooks = serviceFactory.getBookService().findAllBooks();
            req.setAttribute(AttributeName.BOOKS, allBooks);
            return new Router(JspHelper.getPath(JspPath.FIND_ALL_BOOKS), RoutingType.FORWARD);
        } catch (ServiceException e) {
            req.setAttribute(AttributeName.ERROR, "Error in searching books");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
