package com.mathewgv.library.controller.command.impl.user;

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
    private static final String BOOK_ID = "bookId";
    private static final String ISSUE_DATE = "issueDate";
    private static final String DUE_DATE = "dueDate";
    private static final String LOAN_TYPE = "loanType";
    private static final String BOOK_META_ID = "bookMetaId";

    private static final String EMPTY_CONFIRM_VALUE = "";
    private static final String POSITIVE_CONFIRM_VALUE = "y";

    private static final String REDIRECT_TO_LOGIN = "/home?cmd=login";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getSession().getAttribute(AttributeName.USER) == null) {
                return new Router(req.getContextPath() + REDIRECT_TO_LOGIN, RoutingType.REDIRECT);
            }
            req.setAttribute(AttributeName.LOAN_TYPES, getListOfLoanTypes());
            var bookService = serviceFactory.getBookService();
            if (req.getParameter(CONFIRM) == null) {
                var bookMetaId = Integer.parseInt(req.getParameter(BOOK_META_ID));
                bookService.findAnyBookByBookMetaId(bookMetaId)
                        .ifPresent(bookDto -> req.setAttribute(AttributeName.BOOK, bookDto));
            } else if (EMPTY_CONFIRM_VALUE.equals(req.getParameter(CONFIRM))) {
                bookService.findBookById(Integer.parseInt(req.getParameter(BOOK_ID)))
                        .ifPresent(bookDto -> req.setAttribute(AttributeName.BOOK, bookDto));
                var orderCreationDto = buildOrderDto(req);
                req.setAttribute(AttributeName.ORDER_DTO, orderCreationDto);
            } else if (POSITIVE_CONFIRM_VALUE.equals(req.getParameter(CONFIRM))) {
                var orderService = serviceFactory.getOrderService();
                var order = orderService.makeOrder(buildOrderDto(req));
                if (order.getId() != null) {
                    req.setAttribute(AttributeName.ORDER, order);
                }
            }
            return new Router(JspHelper.getPath(JspPath.MAKE_ORDER), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to make the order", e);
            req.setAttribute(AttributeName.ERROR, "Error in making an order");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        } catch (NumberFormatException e) {
            log.error("Failure to process parameter 'bookId' or/and 'bookMetaId', they should be a number", e);
            req.setAttribute(AttributeName.ERROR, "Book ID or/and book-meta ID are not a number");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private List<String> getListOfLoanTypes() {
        return List.of(LoanType.TO_HOME.getValue(),
                LoanType.READING_ROOM.getValue());
    }

    private OrderCreationDto buildOrderDto(HttpServletRequest req) {
        var user = (UserDto) req.getSession().getAttribute(AttributeName.USER);
        return OrderCreationDto.builder()
                .userId(user.getId())
                .bookId(Integer.parseInt(req.getParameter(BOOK_ID)))
                .issueDate(req.getParameter(ISSUE_DATE))
                .dueDate(req.getParameter(DUE_DATE))
                .loanType(req.getParameter(LOAN_TYPE))
                .status(OrderStatus.OPENED.getValue())
                .build();
    }
}
