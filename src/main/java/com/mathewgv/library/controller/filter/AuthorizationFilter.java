package com.mathewgv.library.controller.filter;

import com.mathewgv.library.controller.command.CommandName;
import com.mathewgv.library.service.dto.UserDto;
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
            FIND_ANY_BOOK.convertToString(),
            WRONG_REQUEST.convertToString());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var uri = ((HttpServletRequest) servletRequest).getRequestURI();
        var params = ((HttpServletRequest) servletRequest).getQueryString();
        String addressString;
        if (params != null) {
            addressString = uri + "/" + params;
        } else {
            addressString = uri;
        }
        var homePage = (((HttpServletRequest) servletRequest).getContextPath()) + "/home";
        if (addressString.equals(homePage) || isPublicCmd(addressString) || isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            var prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");

            ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : homePage);
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        var user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null && isAdmin(user);
    }

    private boolean isAdmin(UserDto user) {
        return user.getRole().equals("Админ") || user.getRole().equals("Библиотекарь");
    }

    private boolean isPublicCmd(String addressString) {
        return PUBLIC_CMD.stream().anyMatch(addressString::contains);
    }


}
