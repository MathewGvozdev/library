package com.mathewgv.library.controller.command.impl.user;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.entity.order.OrderStatus;
import com.mathewgv.library.service.dto.OrderCreationDto;
import com.mathewgv.library.service.dto.UserCreationDto;
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
public class UpdateUserInfo implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String CONFIRM = "cfm";
    private static final String ID = "id";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var userService = serviceFactory.getUserService();
            if (req.getParameter(CONFIRM) == null) {
                return new Router(JspHelper.getPath(JspPath.UPDATE_USER_INFO), RoutingType.FORWARD);
            } else {
                var userCreationDto = buildUserCreationDto(req);
                userService.updateUserInfo(userCreationDto);
                return new Router(req.getContextPath() + "/home?cmd=find_user_info&userId=" + userCreationDto.getId(),
                        RoutingType.REDIRECT);
            }
        } catch (ServiceException e) {
            log.error("Failure to update the user", e);
            req.setAttribute(AttributeName.ERROR, "Error in updating");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private UserCreationDto buildUserCreationDto(HttpServletRequest req) {
        return UserCreationDto.builder()
                .id(Integer.parseInt(req.getParameter("id")))
                .firstName(req.getParameter("firstName"))
                .surname(req.getParameter("surname"))
                .telephone(req.getParameter("telephone"))
                .passportNumber(req.getParameter("passport"))
                .password(req.getParameter("password"))
                .build();
    }
}
