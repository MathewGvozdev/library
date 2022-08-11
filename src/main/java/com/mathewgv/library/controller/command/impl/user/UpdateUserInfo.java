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
    private static final String FIRST_NAME = "firstName";
    private static final String SURNAME = "surname";
    private static final String TELEPHONE = "telephone";
    private static final String PASSPORT = "passport";
    private static final String PASSWORD = "password";

    private static final String REDIRECT_TO_USER_INFO = "/home?cmd=find_user_info&userId=";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var userService = serviceFactory.getUserService();
            if (req.getParameter(CONFIRM) == null) {
                return new Router(JspHelper.getPath(JspPath.UPDATE_USER_INFO), RoutingType.FORWARD);
            } else {
                var userCreationDto = buildUserCreationDto(req);
                userService.updateUserInfo(userCreationDto);
                return new Router(req.getContextPath() + REDIRECT_TO_USER_INFO + userCreationDto.getId(),
                        RoutingType.REDIRECT);
            }
        } catch (ServiceException | NumberFormatException e) {
            log.error("Failure to update the user", e);
            req.setAttribute(AttributeName.ERROR, "Error in updating");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    private UserCreationDto buildUserCreationDto(HttpServletRequest req) {
        return UserCreationDto.builder()
                .id(Integer.parseInt(req.getParameter(ID)))
                .firstName(req.getParameter(FIRST_NAME))
                .surname(req.getParameter(SURNAME))
                .telephone(req.getParameter(TELEPHONE))
                .passportNumber(req.getParameter(PASSPORT))
                .password(req.getParameter(PASSWORD))
                .build();
    }
}
