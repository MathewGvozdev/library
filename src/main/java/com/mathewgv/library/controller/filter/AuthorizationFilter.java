package com.mathewgv.library.controller.filter;

import com.mathewgv.library.controller.command.CommandName;
import com.mathewgv.library.service.dto.UserDto;
import com.mathewgv.library.util.AttributeName;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static com.mathewgv.library.controller.command.CommandName.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_CMD = Set.of(
            CHANGE_LOCALE.convertToString(),
            MAKE_ORDER.convertToString(),
            FIND_ALL_BOOK_METAS.convertToString(),
            FIND_ALL_BOOK_METAS_BY_FILTER.convertToString(),
            REGISTRATION.convertToString(),
            LOGIN.convertToString(),
            LOGOUT.convertToString(),
            FIND_USER_INFO.convertToString(),
            UPDATE_USER_INFO.convertToString(),
            WRONG_REQUEST.convertToString()
    );

    private static final Set<String> ADMIN_CMD = Set.of(
            CHANGE_USER_ROLE.convertToString()
    );

    private static final String REFERER = "referer";
    private static final String REDIRECT_TO_HOME = "/home";
    private static final String ADMIN = "Админ";
    private static final String LIBRARIAN = "Библиотекарь";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var uri = ((HttpServletRequest) servletRequest).getRequestURI();
        var params = ((HttpServletRequest) servletRequest).getQueryString();
        String addressString;
        if (params != null) {
            addressString = uri + "?" + params;
        } else {
            addressString = uri;
        }
        var homePage = (((HttpServletRequest) servletRequest).getContextPath()) + REDIRECT_TO_HOME;
        if (addressString.equals(homePage) || isPublicCmd(addressString) || isUserLoggedInAndAdmin(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            var prevPage = ((HttpServletRequest) servletRequest).getHeader(REFERER);
            ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : homePage);
        }
    }

    private boolean isUserLoggedInAndAdmin(ServletRequest servletRequest) {
        var user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute(AttributeName.USER);
        return user != null && (isAdmin(user) || isLibrarian(user));
    }

    private boolean isAdmin(UserDto user) {
        return ADMIN.equals(user.getRole());
    }

    private boolean isLibrarian(UserDto user) {
        return LIBRARIAN.equals(user.getRole());
    }

    private boolean isPublicCmd(String addressString) {
        return PUBLIC_CMD.stream().anyMatch(addressString::contains);
    }
}
