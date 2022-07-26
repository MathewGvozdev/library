package com.mathewgv.library.controller.command.impl.book;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.dto.PublisherDto;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddPublisher implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String TITLE = "title";
    private static final String CITY = "city";
    private static final String CONFIRM = "cfm";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var bookService = serviceFactory.getBookService();
            if (req.getParameter(CONFIRM) == null) {
                return new Router(JspHelper.getPath(JspPath.ADD_PUBLISHER), RoutingType.FORWARD);
            } else if (req.getParameter(CONFIRM).equals("")) {
                var publisherDto = buildPublisherDto(req);
                req.setAttribute(AttributeName.PUBLISHER_DTO, publisherDto);
            } else if (req.getParameter(CONFIRM).equals("y")) {
                var publisher = bookService.addPublisher(buildPublisherDto(req));
                if (publisher.getId() != null) {
                    req.setAttribute(AttributeName.PUBLISHER, publisher);
                    req.setAttribute(AttributeName.RESULT, "success");
                } else {
                    req.setAttribute(AttributeName.RESULT, "failure");
                }
            }
            return new Router(JspHelper.getPath(JspPath.ADD_PUBLISHER), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to add a new publisher", e);
            req.setAttribute(AttributeName.ERROR, "Error in adding author");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private PublisherDto buildPublisherDto(HttpServletRequest req) {
        return PublisherDto.builder()
                .title(req.getParameter(TITLE))
                .city(req.getParameter(CITY))
                .build();
    }
}
