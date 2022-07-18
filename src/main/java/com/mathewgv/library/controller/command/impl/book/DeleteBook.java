package com.mathewgv.library.controller.command.impl.book;

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

public class DeleteBook implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String BOOK_ID = "bookId";
    private static final String CONFIRM = "cfm";

    private static final String JSP_NAME = JspPath.DELETE_BOOK;

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var bookService = serviceFactory.getBookService();
            if (req.getParameter(CONFIRM) == null) {
                return new Router(JspHelper.getPath(JSP_NAME), RoutingType.FORWARD);
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
            return new Router(JspHelper.getPath(JSP_NAME), RoutingType.FORWARD);
        } catch (ServiceException e) {
            req.setAttribute(AttributeName.ERROR, "Error in deleting book");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        } catch (NumberFormatException e) {
            req.setAttribute(AttributeName.ERROR, "BookId is not a number");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
