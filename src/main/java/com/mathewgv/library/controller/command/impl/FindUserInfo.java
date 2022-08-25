package com.mathewgv.library.controller.command.impl;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.entity.OrderStatus;
import com.mathewgv.library.service.dto.OrderDto;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FindUserInfo implements Command {

    private static final String USER_ID = "userId";
    private static final String STATUS = "status";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var userId = Integer.parseInt(req.getParameter(USER_ID));
            var userService = serviceFactory.getUserService();
            var user = userService.findUserInfoById(userId);
            user.ifPresent(userDto -> req.setAttribute(AttributeName.USER_DTO, userDto));

            var status = req.getParameter(STATUS);
            var orderService = serviceFactory.getOrderService();
            var allOrdersByClientId = orderService.findAllOrdersByClientId(userId);
            if (status != null) {
                List<OrderDto> ordersByStatus = filterOrdersByStatus(allOrdersByClientId, status);
                req.setAttribute(AttributeName.ORDERS, ordersByStatus);
            } else {
                req.setAttribute(AttributeName.ORDERS, allOrdersByClientId);
            }
            return new Router(JspHelper.getPath(JspPath.FIND_USER_INFO), RoutingType.FORWARD);
        } catch (ServiceException  e) {
            log.error("Failure to find user info", e);
            req.setAttribute(AttributeName.ERROR, "Error in searching user info");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        } catch (NumberFormatException e) {
            log.error("Failure to process parameter 'userId', it should be a number", e);
            req.setAttribute(AttributeName.ERROR, "UserId is not a number");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private List<OrderDto> filterOrdersByStatus(List<OrderDto> allOrders, String status) {
        List<OrderDto> ordersByStatus = new ArrayList<>();
        for (OrderDto orderDto : allOrders) {
            if (OrderStatus.findByName(orderDto.getStatus()).name().equalsIgnoreCase(status)) {
                ordersByStatus.add(orderDto);
            }
        }
        return ordersByStatus;
    }
}
