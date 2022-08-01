package com.mathewgv.library.controller.command.impl.admin;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
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
public class DeleteBook implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String BOOK_ID = "bookId";
    private static final String CONFIRM = "cfm";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var bookService = serviceFactory.getBookService();
            if (req.getParameter(CONFIRM) == null) {
                return new Router(JspHelper.getPath(JspPath.DELETE_BOOK), RoutingType.FORWARD);
            } else if (req.getParameter(CONFIRM).equals("")) {
                var bookById = bookService.findBookById(Integer.parseInt(req.getParameter(BOOK_ID)));
                if (bookById.isPresent()) {
                    req.setAttribute(AttributeName.BOOK, bookById.orElse(null));
                } else {
                    req.setAttribute(AttributeName.ERROR, "The book doesn't exist");
                }
            } else if (req.getParameter(CONFIRM).equals("y")) {
                var result = bookService.deleteBook(Integer.parseInt(req.getParameter(BOOK_ID)));
                if (result) {
                    req.setAttribute(AttributeName.RESULT, "success");
                } else {
                    req.setAttribute(AttributeName.RESULT, "failure");
                }
            }
            return new Router(JspHelper.getPath(JspPath.DELETE_BOOK), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to delete the book", e);
            req.setAttribute(AttributeName.ERROR, "Error in deleting book");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        } catch (NumberFormatException e) {
            log.error("Failure to process parameter 'bookId', it should be a number", e);
            req.setAttribute(AttributeName.ERROR, "BookId is not a number");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
