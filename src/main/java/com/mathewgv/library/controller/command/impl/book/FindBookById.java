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

public class FindBookById implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String BOOK_ID = "bookId";

    private static final String JSP_NAME = JspPath.FIND_BOOK_BY_ID;

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var bookId = Integer.parseInt(req.getParameter(BOOK_ID));
            var bookDto = serviceFactory.getBookService().findBookById(bookId).orElse(null);
            req.setAttribute(AttributeName.BOOK, bookDto);
            return new Router(JspHelper.getPath(JSP_NAME), RoutingType.FORWARD);
        } catch (ServiceException e) {
            req.setAttribute(AttributeName.ERROR, "Error in searching book with id");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
