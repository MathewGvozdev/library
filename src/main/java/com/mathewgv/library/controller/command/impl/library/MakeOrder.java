package com.mathewgv.library.controller.command.impl.library;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.entity.order.LoanType;
import com.mathewgv.library.entity.order.OrderStatus;
import com.mathewgv.library.service.dto.OrderCreationDto;
import com.mathewgv.library.service.dto.UserDto;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MakeOrder implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String CONFIRM = "cfm";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getSession().getAttribute("user") == null) {
                return new Router(req.getContextPath() + "/home?cmd=login", RoutingType.REDIRECT);
            }
            var libraryService = serviceFactory.getLibraryService();
            var loanTypes = List.of(LoanType.TO_HOME.getValue(), LoanType.READING_ROOM.getValue());
            req.setAttribute("loanTypes", loanTypes);
            if (req.getParameter(CONFIRM) == null) {
                return new Router(JspHelper.getPath(JspPath.MAKE_ORDER), RoutingType.FORWARD);
            } else if (req.getParameter(CONFIRM).equals("")) {
                var orderCreationDto = buildOrderDto(req);
                var bookService = serviceFactory.getBookService();
                bookService.findBookById(Integer.parseInt(req.getParameter("bookId")))
                        .ifPresent(bookDto -> req.setAttribute(AttributeName.BOOK, bookDto));
                req.setAttribute(AttributeName.ORDER_DTO, orderCreationDto);
            } else if (req.getParameter(CONFIRM).equals("y")) {
                var order = libraryService.makeOrder(buildOrderDto(req));
                if (order.getId() != null) {
                    req.setAttribute(AttributeName.ORDER, order);
                    req.setAttribute(AttributeName.RESULT, "success");
                } else {
                    req.setAttribute(AttributeName.RESULT, "failure");
                }
            }
            return new Router(JspHelper.getPath(JspPath.MAKE_ORDER), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to make the order", e);
            req.setAttribute(AttributeName.ERROR, "Error in making an order");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private OrderCreationDto buildOrderDto(HttpServletRequest req) {
        var user = (UserDto) req.getSession().getAttribute("user");
        return OrderCreationDto.builder()
                .userId(user.getId())
                .bookId(Integer.parseInt(req.getParameter("bookId")))
                .issueDate(req.getParameter("issueDate"))
                .dueDate(req.getParameter("dueDate"))
                .loanType(req.getParameter("loanType"))
                .status(OrderStatus.OPENED.getValue())
                .build();
    }
}
