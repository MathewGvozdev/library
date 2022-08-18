package com.mathewgv.library.controller.command.impl.admin;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.dao.filter.BookFilter;
import com.mathewgv.library.service.dto.BookCreationDto;
import com.mathewgv.library.service.dto.BookDto;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

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

    private static final String EMPTY_CONFIRM_VALUE = "";
    private static final String POSITIVE_CONFIRM_VALUE = "y";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (EMPTY_CONFIRM_VALUE.equals(req.getParameter(CONFIRM))) {
                var bookDto = buildBookDto(req);
                var isBookExist = serviceFactory.getBookService().findAllBookMetasByFilter(BookFilter.builder()
                        .limit(1)
                        .page(1)
                        .title(req.getParameter(TITLE))
                        .author(req.getParameter(AUTHORS))
                        .genre(req.getParameter(GENRES))
                        .build());
                if (isBookExist.isEmpty()) {
                    req.setAttribute("isBookExist", false);
                }
                req.setAttribute(AttributeName.BOOK_DTO, bookDto);
            } else if (POSITIVE_CONFIRM_VALUE.equals(req.getParameter(CONFIRM))) {
                var bookService = serviceFactory.getBookService();
                var book = bookService.addBook(buildBookDto(req));
                if (book.getId() != null) {
                    req.setAttribute(AttributeName.BOOK, book);
                }
            }
            return new Router(JspHelper.getPath(JspPath.ADD_BOOK), RoutingType.FORWARD);
        } catch (ServiceException | NumberFormatException e) {
            log.error("Failure to add a new book", e);
            req.setAttribute(AttributeName.ERROR, "Error in adding new book");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        } catch (IOException | ServletException e) {
            log.error("Failure to download image", e);
            req.setAttribute(AttributeName.ERROR, "Error in downloading image");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private BookCreationDto buildBookDto(HttpServletRequest req) throws IOException, ServletException {
        return BookCreationDto.builder()
                .title(req.getParameter(TITLE))
                .authors(req.getParameter(AUTHORS))
                .genres(req.getParameter(GENRES))
                .series(req.getParameter(SERIES))
                .publisher(req.getParameter(PUBLISHER))
                .publisherCity(req.getParameter(PUBLISHER_CITY))
                .pages(Integer.parseInt(req.getParameter(PAGES)))
                .publicationYear(Integer.parseInt(req.getParameter(PUBLICATION_YEAR)))
                .image(req.getPart("image"))
                .build();
    }
}
