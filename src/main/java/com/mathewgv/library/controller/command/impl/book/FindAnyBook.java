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

public class FindAnyBook implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String BOOK_META_ID = "bookMetaId";

    private static final String JSP_NAME = JspPath.FIND_ANY_BOOK;

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var bookMetaId = Integer.parseInt(req.getParameter(BOOK_META_ID));
            var anyBook = serviceFactory.getBookService().findAnyBookByBookMetaId(bookMetaId).orElse(null);
            req.setAttribute(AttributeName.BOOK, anyBook);
            return new Router(JspHelper.getPath(JSP_NAME), RoutingType.FORWARD);
        } catch (ServiceException e) {
            req.setAttribute(AttributeName.ERROR, "Error in searching book");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
