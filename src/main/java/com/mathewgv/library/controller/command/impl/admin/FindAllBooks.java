package com.mathewgv.library.controller.command.impl.admin;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
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
public class FindAllBooks implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final Integer SHOWED_BOOK_LIMIT = 20;
    private static final String PAGE = "page";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            var page = Integer.parseInt(req.getParameter(PAGE));
            var allBooks = serviceFactory.getBookService().findAllBooks(page, SHOWED_BOOK_LIMIT);
            req.setAttribute(AttributeName.BOOKS, allBooks);
            return new Router(JspHelper.getPath(JspPath.FIND_ALL_BOOKS), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to find all books", e);
            req.setAttribute(AttributeName.ERROR, "Error in searching books");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
