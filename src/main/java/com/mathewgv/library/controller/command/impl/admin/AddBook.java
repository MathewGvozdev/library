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
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AddBook implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String CONFIRM = "cfm";
    private static final String TITLE = "title";
    private static final String AUTHORS = "authors";
    private static final String GENRES = "genres";
    private static final String SERIES = "series";
    private static final String PUBLISHER = "publisher";
    private static final String PUBLISHER_CITY = "publisherCity";
    private static final String PAGES = "pages";
    private static final String PUBLICATION_YEAR = "publicationYear";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var bookService = serviceFactory.getBookService();
            if (req.getParameter(CONFIRM) == null) {
                return new Router(JspHelper.getPath(JspPath.ADD_BOOK), RoutingType.FORWARD);
            } else if (req.getParameter(CONFIRM).equals("")) {
                var bookDto = buildBookDto(req);
                req.setAttribute(AttributeName.BOOK_DTO, bookDto);
            } else if (req.getParameter(CONFIRM).equals("y")) {
                var book = bookService.addBookTest(buildBookDto(req));
                if (book.getId() != null) {
                    req.setAttribute(AttributeName.BOOK, book);
                    req.setAttribute(AttributeName.RESULT, "success");
                } else {
                    req.setAttribute(AttributeName.RESULT, "failure");
                }
            }
            return new Router(JspHelper.getPath(JspPath.ADD_BOOK), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to add a new book", e);
            req.setAttribute(AttributeName.ERROR, "Error in adding new book");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private BookDto buildBookDto(HttpServletRequest req) {
        return BookDto.builder()
                .title(req.getParameter(TITLE))
                .authors(req.getParameter(AUTHORS))
                .genres(req.getParameter(GENRES))
                .series(req.getParameter(SERIES))
                .publisher(req.getParameter(PUBLISHER))
                .publisherCity(req.getParameter(PUBLISHER_CITY))
                .pages(Integer.parseInt(req.getParameter(PAGES)))
                .publicationYear(Integer.parseInt(req.getParameter(PUBLICATION_YEAR)))
                .build();
    }
}