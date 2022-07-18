package com.mathewgv.library.controller.command.impl.book;

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

public class UpdateBook implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String BOOK_ID = "bookId";
    private static final String PUBLISHER_ID = "publisherId";
    private static final String BOOK_META_ID = "bookMetaId";
    private static final String PAGES = "pages";
    private static final String PUBLICATION_YEAR = "publicationYear";

    private static final String JSP_NAME = JspPath.UPDATE_BOOK;

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var bookService = serviceFactory.getBookService();
            bookService.updateBook(buildBookDto(req));
            var updatedBook = bookService.findBookById(Integer.parseInt(req.getParameter(BOOK_ID))).orElse(null);
            req.setAttribute(AttributeName.BOOK, updatedBook);
            return new Router(JspHelper.getPath(JSP_NAME), RoutingType.FORWARD);
        } catch (ServiceException e) {
            req.setAttribute(AttributeName.ERROR, "Error in updating the book");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private BookDto buildBookDto(HttpServletRequest req) {
        return BookDto.builder()
                .id(Integer.parseInt(req.getParameter(BOOK_ID)))
                .publisherId(Integer.parseInt(req.getParameter(PUBLISHER_ID)))
                .bookMetaId(Integer.parseInt(req.getParameter(BOOK_META_ID)))
                .pages(Integer.parseInt(req.getParameter(PAGES)))
                .publicationYear(Integer.parseInt(req.getParameter(PUBLICATION_YEAR)))
                .build();
    }
}
