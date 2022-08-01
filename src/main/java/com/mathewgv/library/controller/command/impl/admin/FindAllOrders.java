package com.mathewgv.library.controller.command.impl.admin;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.entity.order.OrderStatus;
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
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FindAllOrders implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String CLIENT_ID = "clientId";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var clientId = req.getParameter(CLIENT_ID);
            var status = req.getParameter("status");
            if (clientId == null) {
                var allOrders = serviceFactory.getLibraryService().findAllOrders();
                if (status == null) {
                    req.setAttribute(AttributeName.ORDERS, allOrders);
                } else {
                    List<OrderDto> ordersByStatus = filterOrdersByStatus(allOrders, status);
                    req.setAttribute(AttributeName.ORDERS, ordersByStatus);
                }
            } else {
                var ordersByClientId = serviceFactory.getLibraryService().findAllOrdersByClientId(Integer.parseInt(clientId));
                if (status == null) {
                    req.setAttribute(AttributeName.ORDERS, ordersByClientId);
                } else {
                    List<OrderDto> ordersByStatus = filterOrdersByStatus(ordersByClientId, status);
                    req.setAttribute(AttributeName.ORDERS, ordersByStatus);
                }

            }
            return new Router(JspHelper.getPath(JspPath.FIND_ALL_ORDERS), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to find all orders", e);
            req.setAttribute(AttributeName.ERROR, "Error in searching orders");
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
