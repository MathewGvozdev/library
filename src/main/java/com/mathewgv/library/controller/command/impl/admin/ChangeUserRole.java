package com.mathewgv.library.controller.command.impl.admin;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.entity.user.Role;
import com.mathewgv.library.service.dto.RoleDto;
import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.service.dto.UserDto;
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
public class ChangeUserRole implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String CONFIRM = "cfm";
    private static final String ID = "id";
    private static final String ROLE = "role";

    private static final String REDIRECT_TO_USER_INFO = "/home?cmd=find_user_info&userId=";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
//            req.setAttribute("roles", List.of("Админ",
//                    "Библиотекарь",
//                    "Пользователь"));
            var userService = serviceFactory.getUserService();
            var allRoles = userService.findAllRoles().stream()
                    .map(RoleDto::getTitle)
                    .toList();
            req.setAttribute(AttributeName.ROLES, allRoles);
            if (req.getParameter(CONFIRM) == null) {
                var userInfoById = userService.findUserInfoById(Integer.parseInt(req.getParameter(ID)));
                userInfoById.ifPresent(userDto -> req.setAttribute(AttributeName.USER, userDto));
                return new Router(JspHelper.getPath(JspPath.CHANGE_USER_ROLE), RoutingType.FORWARD);
            } else {
                var userCreationDto = buildUserCreationDto(req);
                userService.updateUserRole(userCreationDto);
                var redirectionWebPage = req.getContextPath() + REDIRECT_TO_USER_INFO + userCreationDto.getId();
                return new Router(redirectionWebPage, RoutingType.REDIRECT);
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
                .role(req.getParameter(ROLE))
                .build();
    }
}
