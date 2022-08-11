package com.mathewgv.library.controller.command.impl.admin;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.BookService;
import com.mathewgv.library.service.LibraryService;
import com.mathewgv.library.service.dto.OrderDto;
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
import java.util.Optional;

@Slf4j
public class FindBookById implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String BOOK_ID = "bookId";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getParameter(BOOK_ID) != null) {
                var bookId = Integer.parseInt(req.getParameter(BOOK_ID));
                var bookService = serviceFactory.getBookService();
                var bookDto = bookService.findBookById(bookId);
                if (bookDto.isPresent()) {
                    req.setAttribute(AttributeName.BOOK, bookDto.get());
                    var libraryService = serviceFactory.getLibraryService();
                    var order = libraryService.findOrderIfBookIsLoaned(bookId);
                    order.ifPresent(orderDto -> req.setAttribute("order", orderDto));
                }
            }
            return new Router(JspHelper.getPath(JspPath.FIND_BOOK_BY_ID), RoutingType.FORWARD);
        } catch (ServiceException | NumberFormatException e) {
            log.error("Failure to find book by ID", e);
            req.setAttribute(AttributeName.ERROR, "Error in searching book with id");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
