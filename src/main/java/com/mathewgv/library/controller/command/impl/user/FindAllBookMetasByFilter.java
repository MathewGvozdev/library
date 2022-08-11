package com.mathewgv.library.controller.command.impl.user;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.dao.filter.BookFilter;
import com.mathewgv.library.service.BookService;
import com.mathewgv.library.service.dto.BookDto;
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
import java.util.List;

@Slf4j
public class FindAllBookMetasByFilter implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String TITLE = "title";
    private static final String AUTHORS = "authors";
    private static final String GENRES = "genres";
    private static final String SERIES = "series";
    private static final String PAGE = "page";
    private static final Integer SHOWED_BOOK_METAS_LIMIT = 10;

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var bookService = serviceFactory.getBookService();
            var page = Integer.parseInt(req.getParameter(PAGE));
            var showedBooksByFilter = bookService.findAllBookMetasByFilter(buildBookFilter(req, page));
            var booksOnNextPage = bookService.findAllBookMetasByFilter(buildBookFilter(req, page + 1));
            setNextButtonIfNotEmpty(req, booksOnNextPage);
            req.setAttribute(AttributeName.BOOK_METAS, showedBooksByFilter);
            return new Router(JspHelper.getPath(JspPath.FIND_ALL_BOOK_METAS_BY_FILTER), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to find all book-metas by filter", e);
            req.setAttribute(AttributeName.ERROR, "Error in searching books with filter");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private void setNextButtonIfNotEmpty(HttpServletRequest req, List<BookDto> booksOnNextPage) {
        if (booksOnNextPage.size() == 0) {
            req.setAttribute("hasNextBtn", false);
        } else {
            req.setAttribute("hasNextBtn", true);
        }
    }

    private Integer countPages(List<BookDto> totalBooks) {
        int pages;
        if (totalBooks.size() % SHOWED_BOOK_METAS_LIMIT == 0) {
            pages = totalBooks.size() / SHOWED_BOOK_METAS_LIMIT;
        } else {
            pages = totalBooks.size() / SHOWED_BOOK_METAS_LIMIT + 1;
        }
        return pages;
    }

    private BookFilter buildBookFilter(HttpServletRequest req, Integer page) {
        return BookFilter.builder()
                .page(page)
                .limit(SHOWED_BOOK_METAS_LIMIT)
                .title(req.getParameter(TITLE))
                .author(req.getParameter(AUTHORS))
                .genre(req.getParameter(GENRES))
                .series(req.getParameter(SERIES))
                .build();
    }
}
