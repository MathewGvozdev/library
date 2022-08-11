package com.mathewgv.library.controller.command.impl.user;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.entity.order.OrderStatus;
import com.mathewgv.library.service.LibraryService;
import com.mathewgv.library.service.UserService;
import com.mathewgv.library.service.dto.OrderDto;
import com.mathewgv.library.service.dto.UserDto;
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
import java.util.Optional;

@Slf4j
public class FindUserInfo implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String USER_ID = "userId";
    private static final String STATUS = "status";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var userId = Integer.parseInt(req.getParameter(USER_ID));
            var userService = serviceFactory.getUserService();
            var user = userService.findUserInfoById(userId);
            user.ifPresent(userDto -> req.setAttribute(AttributeName.USER_DTO, userDto));

            var status = req.getParameter(STATUS);
            var libraryService = serviceFactory.getLibraryService();
            var allOrdersByClientId = libraryService.findAllOrdersByClientId(userId);
            if (status == null) {
                req.setAttribute(AttributeName.ORDERS, allOrdersByClientId);
            } else {
                List<OrderDto> ordersByStatus = filterOrdersByStatus(allOrdersByClientId, status);
                req.setAttribute(AttributeName.ORDERS, ordersByStatus);
            }

            return new Router(JspHelper.getPath(JspPath.FIND_USER_INFO), RoutingType.FORWARD);
        } catch (ServiceException | NumberFormatException e) {
            log.error("Failure to find any book", e);
            req.setAttribute(AttributeName.ERROR, "Error in searching book");
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
