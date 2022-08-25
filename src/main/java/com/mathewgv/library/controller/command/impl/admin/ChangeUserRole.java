package com.mathewgv.library.controller.command.impl.admin;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.dto.RoleDto;
import com.mathewgv.library.service.dto.UserCreationDto;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import com.mathewgv.library.util.UrlPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChangeUserRole implements Command {

    private static final String CONFIRM = "cfm";
    private static final String ID = "id";
    private static final String ROLE = "role";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var userService = serviceFactory.getUserService();
            var allRoles = userService.findAllRoles().stream()
                    .map(RoleDto::getTitle)
                    .toList();
            req.setAttribute(AttributeName.ROLES, allRoles);
            var userDtoById = userService.findUserInfoById(Integer.parseInt(req.getParameter(ID)));
            userDtoById.ifPresent(userDto -> req.setAttribute(AttributeName.USER, userDto));
            if (req.getParameter(CONFIRM) != null) {
                var userCreationDto = buildUserCreationDto(req);
                userService.updateUserRole(userCreationDto);
                var redirectionWebPage = req.getContextPath() + UrlPath.FIND_USER_INFO + userCreationDto.getId();
                return new Router(redirectionWebPage, RoutingType.REDIRECT);
            }
            return new Router(JspHelper.getPath(JspPath.CHANGE_USER_ROLE), RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Failure to update the user", e);
            req.setAttribute(AttributeName.ERROR, "Error in updating");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        } catch (NumberFormatException e) {
            log.error("Failure to process parameter 'id', it should be a number", e);
            req.setAttribute(AttributeName.ERROR, "ID is not a number");
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
