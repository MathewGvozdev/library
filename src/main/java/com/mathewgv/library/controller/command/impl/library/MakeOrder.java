package com.mathewgv.library.controller.command.impl.library;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.dto.BookDto;
import com.mathewgv.library.service.dto.OrderCreationDto;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MakeOrder implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String CONFIRM = "cfm";
    private static final String BOOK_META_ID = "bookMetaId";
    private static final String PUBLISHER_ID = "publisherId";
    private static final String PAGES = "pages";
    private static final String PUBLICATION_YEAR = "publicationYear";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var libraryService = serviceFactory.getLibraryService();
            if (req.getParameter(CONFIRM) == null) {
                return new Router(JspHelper.getPath(JspPath.MAKE_ORDER), RoutingType.FORWARD);
            } else if (req.getParameter(CONFIRM).equals("")) {
                var orderCreationDto = buildOrderDto(req);
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
            req.setAttribute(AttributeName.ERROR, "Error in making an order");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private OrderCreationDto buildOrderDto(HttpServletRequest req) {
        return OrderCreationDto.builder()
                .userId(Integer.parseInt(req.getParameter("userId")))
                .bookId(Integer.parseInt(req.getParameter("bookId")))
                .issueDate(req.getParameter("issueDate"))
                .dueDate(req.getParameter("dueDate"))
                .factDate(req.getParameter("factDate"))
                .loanType(req.getParameter("loanType"))
                .status(req.getParameter("status"))
                .build();
    }
}
