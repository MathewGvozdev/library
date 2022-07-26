package com.mathewgv.library.controller.command.impl.library;

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
public class FindAllOrders implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var clientId = req.getParameter("clientId");
            if (clientId == null) {
                var allOrders = serviceFactory.getLibraryService().findAllOrders();
                req.setAttribute(AttributeName.ORDERS, allOrders);
            } else {
                var ordersByClientId = serviceFactory.getLibraryService().findAllOrdersByClientId(Integer.parseInt(clientId));
                req.setAttribute(AttributeName.ORDERS, ordersByClientId);
            }
            return new Router(JspHelper.getPath(JspPath.FIND_ALL_ORDERS), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to find all orders", e);
            req.setAttribute(AttributeName.ERROR, "Error in searching orders");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
