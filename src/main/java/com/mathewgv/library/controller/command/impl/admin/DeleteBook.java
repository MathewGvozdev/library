package com.mathewgv.library.controller.command.impl.admin;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteBook implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String CONFIRM = "cfm";
    private static final String BOOK_ID = "bookId";

    private static final String EMPTY_CONFIRM_VALUE = "";
    private static final String POSITIVE_CONFIRM_VALUE = "y";

    private static final String NOT_FOUND_MSG = "The book doesn't exist";
    private static final String DELETING_SUCCESS = "success";
    private static final String DELETING_FAILURE = "failure";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var bookService = serviceFactory.getBookService();
            if (EMPTY_CONFIRM_VALUE.equals(req.getParameter(CONFIRM))) {
                var bookById = bookService.findBookById(Integer.parseInt(req.getParameter(BOOK_ID)));
                if (bookById.isPresent()) {
                    req.setAttribute(AttributeName.BOOK, bookById.get());
                } else {
                    req.setAttribute(AttributeName.ERROR, NOT_FOUND_MSG);
                }
            } else if (POSITIVE_CONFIRM_VALUE.equals(req.getParameter(CONFIRM))) {
                var result = bookService.deleteBook(Integer.parseInt(req.getParameter(BOOK_ID)));
                if (result) {
                    req.setAttribute(AttributeName.RESULT, DELETING_SUCCESS);
                } else {
                    req.setAttribute(AttributeName.RESULT, DELETING_FAILURE);
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
