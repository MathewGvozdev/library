package com.mathewgv.library.controller.command.impl.book;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.dto.GenreDto;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddGenre implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String TITLE = "title";
    private static final String CONFIRM = "cfm";

    private static final String JSP_NAME = JspPath.ADD_GENRE;

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var bookService = serviceFactory.getBookService();
            if (req.getParameter(CONFIRM) == null) {
                return new Router(JspHelper.getPath(JSP_NAME), RoutingType.FORWARD);
            } else if (req.getParameter(CONFIRM).equals("")) {
                var genreDto = buildGenreDto(req);
                req.setAttribute(AttributeName.GENRE_DTO, genreDto);
            } else if (req.getParameter(CONFIRM).equals("y")) {
                var genre = bookService.addGenre(buildGenreDto(req));
                if (genre.getId() != null) {
                    req.setAttribute(AttributeName.GENRE, genre);
                    req.setAttribute(AttributeName.RESULT, "success");
                } else {
                    req.setAttribute(AttributeName.RESULT, "failure");
                }
            }
            return new Router(JspHelper.getPath(JSP_NAME), RoutingType.FORWARD);
        } catch (ServiceException e) {
            req.setAttribute(AttributeName.ERROR, "Error in adding new genre");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private GenreDto buildGenreDto(HttpServletRequest req) {
        return GenreDto.builder()
                .title(req.getParameter(TITLE))
                .build();
    }
}
