package com.mathewgv.library.controller.command.impl.admin;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.entity.order.OrderStatus;
import com.mathewgv.library.service.LibraryService;
import com.mathewgv.library.service.dto.BookDto;
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
    private static final Integer SHOWED_ORDERS_LIMIT = 20;
    private static final String PAGE = "page";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var clientId = req.getParameter(CLIENT_ID);
            var status = req.getParameter("status");
            var page = Integer.parseInt(req.getParameter(PAGE));
            var libraryService = serviceFactory.getLibraryService();
            if (clientId == null) {
                var showedOrders = libraryService.findAllOrders(page, SHOWED_ORDERS_LIMIT);
                var totalOrders = libraryService.findAllOrders();
                if (status.equals("all")) {
                    req.setAttribute("pages", countPages(totalOrders));
                    req.setAttribute(AttributeName.ORDERS, showedOrders);
                } else {
                    List<OrderDto> totalOrdersByStatus = filterOrdersByStatus(totalOrders, status);
                    if (totalOrdersByStatus.size() < SHOWED_ORDERS_LIMIT) {
                        req.setAttribute(AttributeName.ORDERS, totalOrdersByStatus);
                    } else {
                        var pages = countPages(totalOrdersByStatus);
                        req.setAttribute("pages", pages);
                        showedOrders = totalOrdersByStatus.subList((page - 1) * SHOWED_ORDERS_LIMIT, page * SHOWED_ORDERS_LIMIT);
                        req.setAttribute(AttributeName.ORDERS, showedOrders);
                    }
                }
            } else {
                var ordersByClientId = libraryService.findAllOrdersByClientId(Integer.parseInt(clientId));
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

    private Integer countPages(List<OrderDto> totalOrders) {
        int pages;
        if (totalOrders.size() % SHOWED_ORDERS_LIMIT == 0) {
            pages = totalOrders.size() / SHOWED_ORDERS_LIMIT;
        } else {
            pages = totalOrders.size() / SHOWED_ORDERS_LIMIT + 1;
        }
        return pages;
    }

    private List<OrderDto> filterOrdersByStatus(List<OrderDto> orders, String status) {
        List<OrderDto> ordersByStatus = new ArrayList<>();
        for (OrderDto orderDto : orders) {
            if (OrderStatus.findByName(orderDto.getStatus()).name().equalsIgnoreCase(status)) {
                ordersByStatus.add(orderDto);
            }
        }
        return ordersByStatus;
    }
}
