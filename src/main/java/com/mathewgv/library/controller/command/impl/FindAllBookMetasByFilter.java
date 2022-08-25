package com.mathewgv.library.controller.command.impl;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.dao.filter.BookFilter;
import com.mathewgv.library.service.dto.BookCreationDto;
import com.mathewgv.library.service.dto.BookDto;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.service.validator.ValidationException;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class FindAllBookMetasByFilter implements Command {

    private static final String PAGE = "page";
    private static final String TITLE = "title";
    private static final String AUTHORS = "authors";
    private static final String GENRES = "genres";
    private static final String SERIES = "series";

    private static final String EMPTY_PARAM_VALUE = "";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var page = Integer.parseInt(req.getParameter(PAGE));
            var bookService = serviceFactory.getBookService();
            var showedBooksByFilter = bookService.findAllBookMetasByFilter(buildBookCreationDto(req), page);
            var booksOnNextPage = bookService.findAllBookMetasByFilter(buildBookCreationDto(req), page + 1);
            setNextButtonIfNextPageNotEmpty(req, booksOnNextPage);
            req.setAttribute(AttributeName.BOOK_METAS, showedBooksByFilter);
            return new Router(JspHelper.getPath(JspPath.FIND_ALL_BOOK_METAS_BY_FILTER), RoutingType.FORWARD);
        } catch (ValidationException e) {
            req.setAttribute(AttributeName.ERRORS, e.getErrors());
            return new Router(JspHelper.getPath(JspPath.HOME), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to find all book-metas by filter", e);
            req.setAttribute(AttributeName.ERROR, "Error in searching books with filter");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        } catch (NumberFormatException e) {
            log.error("Failure to process parameter 'page', it should be a number", e);
            req.setAttribute(AttributeName.ERROR, "Page is not a number");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private void setNextButtonIfNextPageNotEmpty(HttpServletRequest req, List<BookDto> booksOnNextPage) {
        if (booksOnNextPage.isEmpty()) {
            req.setAttribute(AttributeName.HAS_NEXT_BTN, false);
        } else {
            req.setAttribute(AttributeName.HAS_NEXT_BTN, true);
        }
    }

    private BookCreationDto buildBookCreationDto(HttpServletRequest req) {
        String title = req.getParameter(TITLE);
        String authors = req.getParameter(AUTHORS);
        String genres = req.getParameter(GENRES);
        String series = req.getParameter(SERIES);
        if (EMPTY_PARAM_VALUE.equals(title)) {
            title = null;
        }
        if (EMPTY_PARAM_VALUE.equals(authors)) {
            authors = null;
        }
        if (EMPTY_PARAM_VALUE.equals(genres)) {
            genres = null;
        }
        if (EMPTY_PARAM_VALUE.equals(series)) {
            series = null;
        }
        return BookCreationDto.builder()
                .title(title)
                .authors(authors)
                .genres(genres)
                .series(series)
                .build();
    }
}
