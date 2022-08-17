package com.mathewgv.library.controller.command.impl.admin;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.entity.order.OrderStatus;
import com.mathewgv.library.service.dto.OrderCreationDto;
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
public class UpdateOrder implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String CONFIRM = "cfm";
    private static final String ID = "id";
    private static final String CLIENT_ID = "clientId";
    private static final String BOOK_ID = "bookId";
    private static final String ISSUE_DATE = "issueDate";
    private static final String DUE_DATE = "dueDate";
    private static final String FACT_DATE = "factDate";
    private static final String LOAN_TYPE = "loanType";
    private static final String STATUS = "status";

    private static final String REDIRECT_TO_ALL_ORDERS = "/home?cmd=find_all_orders&page=1&status=all";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute(AttributeName.STATUSES, getListOfStatuses());
            var orderService = serviceFactory.getOrderService();
            if (req.getParameter(CONFIRM) == null) {
                var order = orderService.findOrderById(Long.parseLong(req.getParameter(ID)));
                order.ifPresent(orderDto -> req.setAttribute(AttributeName.ORDER, orderDto));
                return new Router(JspHelper.getPath(JspPath.UPDATE_ORDER), RoutingType.FORWARD);
            } else {
                orderService.updateOrder(buildOrderDto(req));
                return new Router(req.getContextPath() + REDIRECT_TO_ALL_ORDERS, RoutingType.REDIRECT);
            }
        } catch (ServiceException | NumberFormatException e) {
            log.error("Failure to update the order", e);
            req.setAttribute(AttributeName.ERROR, "Error in updating");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private List<String> getListOfStatuses() {
        return List.of(OrderStatus.OPENED.getValue(),
                OrderStatus.DECLINED.getValue(),
                OrderStatus.LOANED.getValue(),
                OrderStatus.CLOSED.getValue(),
                OrderStatus.OVERDUE.getValue());
    }

    private OrderCreationDto buildOrderDto(HttpServletRequest req) {
        return OrderCreationDto.builder()
                .id(Long.parseLong(req.getParameter(ID)))
                .userId(Integer.parseInt(req.getParameter(CLIENT_ID)))
                .bookId(Integer.parseInt(req.getParameter(BOOK_ID)))
                .issueDate(req.getParameter(ISSUE_DATE))
                .dueDate(req.getParameter(DUE_DATE))
                .factDate(req.getParameter(FACT_DATE))
                .loanType(req.getParameter(LOAN_TYPE))
                .status(req.getParameter(STATUS))
                .build();
    }
}
