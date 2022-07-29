package com.mathewgv.library.controller.command.impl.library;

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
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class UpdateOrder implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String CONFIRM = "cfm";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var statuses = List.of(OrderStatus.OPENED.getValue(),
                    OrderStatus.DECLINED.getValue(),
                    OrderStatus.LOANED.getValue(),
                    OrderStatus.CLOSED.getValue(),
                    OrderStatus.OVERDUE.getValue());
            req.setAttribute("statuses", statuses);
            var libraryService = serviceFactory.getLibraryService();
            if (req.getParameter(CONFIRM) == null) {
                libraryService.findOrderById(Long.parseLong(req.getParameter("id")))
                        .ifPresent(orderDto -> req.setAttribute("order", orderDto));
                return new Router(JspHelper.getPath(JspPath.UPDATE_ORDER), RoutingType.FORWARD);
            } else {
                libraryService.updateOrder(buildOrderDto(req));
                return new Router(req.getContextPath() + "/home?cmd=find_all_orders", RoutingType.REDIRECT);
            }
        } catch (ServiceException e) {
            log.error("Failure to update the order", e);
            req.setAttribute(AttributeName.ERROR, "Error in updating");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private OrderCreationDto buildOrderDto(HttpServletRequest req) {
        return OrderCreationDto.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .userId(Integer.parseInt(req.getParameter("clientId")))
                .bookId(Integer.parseInt(req.getParameter("bookId")))
                .issueDate(req.getParameter("issueDate"))
                .dueDate(req.getParameter("dueDate"))
                .factDate(req.getParameter("factDate"))
                .loanType(req.getParameter("loanType"))
                .status(req.getParameter("status"))
                .build();
    }
}
