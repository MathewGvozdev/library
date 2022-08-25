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
public class FindBookById implements Command {

    private static final String BOOK_ID = "bookId";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getParameter(BOOK_ID) != null) {
                var bookId = Integer.parseInt(req.getParameter(BOOK_ID));
                var bookService = serviceFactory.getBookService();
                var bookDto = bookService.findBookById(bookId);
                if (bookDto.isPresent()) {
                    req.setAttribute(AttributeName.BOOK, bookDto.get());
                    var orderService = serviceFactory.getOrderService();
                    var order = orderService.findOrderIfBookIsLoaned(bookId);
                    order.ifPresent(orderDto -> req.setAttribute(AttributeName.ORDER, orderDto));
                }
            }
            return new Router(JspHelper.getPath(JspPath.FIND_BOOK_BY_ID), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to find book by ID", e);
            req.setAttribute(AttributeName.ERROR, "Error in searching book with id");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        } catch (NumberFormatException e) {
            log.error("Failure to process parameter 'bookId', it should be a number", e);
            req.setAttribute(AttributeName.ERROR, "BookId is not a number");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
