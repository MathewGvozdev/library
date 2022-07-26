package com.mathewgv.library.controller.command.impl.book;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.dao.filter.BookFilter;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindAllBookMetasByFilter implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String TITLE = "title";
    private static final String AUTHORS = "authors";
    private static final String GENRES = "genres";
    private static final String SERIES = "series";
    private static final String PAGE = "page";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var bookService = serviceFactory.getBookService();
            var allBooksByFilter = bookService.findAllBookMetasByFilter(buildBookFilter(req));
            req.setAttribute(AttributeName.BOOK_METAS, allBooksByFilter);
            return new Router(JspHelper.getPath(JspPath.FIND_ALL_BOOK_METAS_BY_FILTER), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to find all book-metas by filter", e);
            req.setAttribute(AttributeName.ERROR, "Error in searching books with filter");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private BookFilter buildBookFilter(HttpServletRequest req) {
        return BookFilter.builder()
                .page(Integer.parseInt(req.getParameter(PAGE)))
                .title(req.getParameter(TITLE))
                .author(req.getParameter(AUTHORS))
                .genre(req.getParameter(GENRES))
                .series(req.getParameter(SERIES))
                .build();
    }
}
