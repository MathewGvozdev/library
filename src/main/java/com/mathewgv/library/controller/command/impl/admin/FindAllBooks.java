package com.mathewgv.library.controller.command.impl.admin;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.dto.BookDto;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class FindAllBooks implements Command {

    private static final String PAGE = "page";

    private static final Integer SHOWED_BOOK_LIMIT = 20;

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            var page = Integer.parseInt(req.getParameter(PAGE));
            var bookService = serviceFactory.getBookService();
            var showedBooks = bookService.findAllBooks(page, SHOWED_BOOK_LIMIT);
            var totalBooks = bookService.findAllBooks();
            req.setAttribute(AttributeName.PAGES, countPages(totalBooks));
            req.setAttribute(AttributeName.BOOKS, showedBooks);
            return new Router(JspHelper.getPath(JspPath.FIND_ALL_BOOKS), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to find all books", e);
            req.setAttribute(AttributeName.ERROR, "Error in searching books");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        } catch (NumberFormatException e) {
            log.error("Failure to process parameter 'page', it should be a number", e);
            req.setAttribute(AttributeName.ERROR, "Page is not a number");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private Integer countPages(List<BookDto> totalBooks) {
        int pages;
        if (totalBooks.size() % SHOWED_BOOK_LIMIT == 0) {
            pages = totalBooks.size() / SHOWED_BOOK_LIMIT;
        } else {
            pages = totalBooks.size() / SHOWED_BOOK_LIMIT + 1;
        }
        return pages;
    }
}
