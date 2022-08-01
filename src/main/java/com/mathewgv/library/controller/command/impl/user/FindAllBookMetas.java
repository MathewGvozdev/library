package com.mathewgv.library.controller.command.impl.user;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.BookService;
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
public class FindAllBookMetas implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final Integer SHOWED_BOOK_METAS_LIMIT = 10;
    private static final String PAGE = "page";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            var page = Integer.parseInt(req.getParameter(PAGE));
            var bookService = serviceFactory.getBookService();
            var allBookMetas = bookService.findAllBookMetas(page, SHOWED_BOOK_METAS_LIMIT);
            req.setAttribute(AttributeName.BOOK_METAS, allBookMetas);
            return new Router(JspHelper.getPath(JspPath.FIND_ALL_BOOK_METAS), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to find all book-metas", e);
            req.setAttribute(AttributeName.ERROR, "Error in searching book metas");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
