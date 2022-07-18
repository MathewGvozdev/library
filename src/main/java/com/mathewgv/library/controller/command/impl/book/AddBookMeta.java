package com.mathewgv.library.controller.command.impl.book;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.dto.BookMetaDto;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddBookMeta implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String TITLE = "title";
    private static final String AUTHORS = "authors";
    private static final String GENRES = "genres";
    private static final String SERIES = "series";
    private static final String CONFIRM = "cfm";

    private static final String JSP_NAME = JspPath.ADD_BOOK_META;

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var bookService = serviceFactory.getBookService();
            if (req.getParameter(CONFIRM) == null) {
                return new Router(JspHelper.getPath(JSP_NAME), RoutingType.FORWARD);
            } else if (req.getParameter(CONFIRM).equals("")) {
                var bookMetaDto = buildBookMetaDto(req);
                req.setAttribute(AttributeName.BOOK_META_DTO, bookMetaDto);
            } else if (req.getParameter(CONFIRM).equals("y")) {
                var bookMeta = bookService.addBookMeta(buildBookMetaDto(req));
                if (bookMeta.getId() != null) {
                    req.setAttribute(AttributeName.BOOK_META, bookMeta);
                    req.setAttribute(AttributeName.RESULT, "success");
                } else {
                    req.setAttribute(AttributeName.RESULT, "failure");
                }
            }
            return new Router(JspHelper.getPath(JSP_NAME), RoutingType.FORWARD);
        } catch (ServiceException e) {
            req.setAttribute(AttributeName.ERROR, "Error in adding book-meta");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
//        var bookService = serviceFactory.getBookService();
//        try {
//            if (req.getParameter(TITLE) != null) {
//                var bookMeta = bookService.addBookMeta(buildBookMetaDto(req));
//                req.setAttribute("bookMeta", bookMeta);
//            }
//            return new Router(JspHelper.getPath(JSP_NAME), RoutingType.FORWARD);
//        } catch (ServiceException e) {
//            req.setAttribute("error", "Error in adding new book-meta");
//            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
//        }
    }

    private BookMetaDto buildBookMetaDto(HttpServletRequest req) {
        String series;
        if (req.getParameter(SERIES).equals("")) {
            series = null;
        } else {
            series = req.getParameter(SERIES);
        }
        return BookMetaDto.builder()
                .title(req.getParameter(TITLE))
                .authors(req.getParameter(AUTHORS))
                .genres(req.getParameter(GENRES))
                .series(series)
                .build();
    }
}
