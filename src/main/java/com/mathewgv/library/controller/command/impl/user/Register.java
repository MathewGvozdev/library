package com.mathewgv.library.controller.command.impl.user;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.dto.AuthorDto;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Register implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String CONFIRM = "cfm";
    private static final String AUTHOR_NAME = "aName";
    private static final String AUTHOR_SURNAME = "aSurname";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var bookService = serviceFactory.getBookService();
            if (req.getParameter(CONFIRM) == null) {
                return new Router(JspHelper.getPath(JspPath.ADD_AUTHOR), RoutingType.FORWARD);
            } else if (req.getParameter(CONFIRM).equals("")) {
                var authorDto = buildAuthorDto(req);
                req.setAttribute(AttributeName.AUTHOR_DTO, authorDto);
            } else if (req.getParameter(CONFIRM).equals("y")) {
                var author = bookService.addAuthor(buildAuthorDto(req));
                if (author.getId() != null) {
                    req.setAttribute(AttributeName.AUTHOR, author);
                    req.setAttribute(AttributeName.RESULT, "success");
                } else {
                    req.setAttribute(AttributeName.RESULT, "failure");
                }
            }
            return new Router(JspHelper.getPath(JspPath.ADD_AUTHOR), RoutingType.FORWARD);
        } catch (ServiceException e) {
            req.setAttribute(AttributeName.ERROR, "Error in adding author");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private AuthorDto buildAuthorDto(HttpServletRequest req) {
        return AuthorDto.builder()
                .name(req.getParameter(AUTHOR_NAME))
                .surname(req.getParameter(AUTHOR_SURNAME))
                .build();
    }
}
