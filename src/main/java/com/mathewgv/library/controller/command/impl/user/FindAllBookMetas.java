package com.mathewgv.library.controller.command.impl.user;

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
public class FindAllBookMetas implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String PAGE = "page";

    private static final Integer SHOWED_BOOK_METAS_LIMIT = 10;

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            var page = Integer.parseInt(req.getParameter(PAGE));
            var bookService = serviceFactory.getBookService();
            var showedBookMetas = bookService.findAllBookMetas(page, SHOWED_BOOK_METAS_LIMIT);
            var totalBookMetas = bookService.findAllBookMetas();
            req.setAttribute(AttributeName.PAGES, countPages(totalBookMetas));
            req.setAttribute(AttributeName.BOOK_METAS, showedBookMetas);
            return new Router(JspHelper.getPath(JspPath.FIND_ALL_BOOK_METAS), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to find all book-metas", e);
            req.setAttribute(AttributeName.ERROR, "Error in searching book metas");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        } catch (NumberFormatException e) {
            log.error("Failure to process parameter 'page', it should be a number", e);
            req.setAttribute(AttributeName.ERROR, "Page is not a number");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private Integer countPages(List<BookDto> totalBookMetas) {
        int pages;
        if (totalBookMetas.size() % SHOWED_BOOK_METAS_LIMIT == 0) {
            pages = totalBookMetas.size() / SHOWED_BOOK_METAS_LIMIT;
        } else {
            pages = totalBookMetas.size() / SHOWED_BOOK_METAS_LIMIT + 1;
        }
        return pages;
    }
}
