package com.mathewgv.library.controller.filter;

import com.mathewgv.library.service.dto.UserDto;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.UrlPath;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static com.mathewgv.library.controller.command.CommandName.*;

@WebFilter(UrlPath.HOME)
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_CMD = Set.of(
            LOGIN.convertToParam(),
            LOGOUT.convertToParam(),
            REGISTRATION.convertToParam(),
            CHANGE_LOCALE.convertToParam(),
            FIND_ALL_BOOK_METAS.convertToParam(),
            FIND_ALL_BOOK_METAS_BY_FILTER.convertToParam(),
            MAKE_ORDER.convertToParam(),
            FIND_USER_INFO.convertToParam(),
            UPDATE_USER_INFO.convertToParam(),
            WRONG_REQUEST.convertToParam()
    );

    private static final String REFERER = "referer";
    private static final String ADMIN = "Админ";
    private static final String LIBRARIAN = "Библиотекарь";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestedWebPage = getWebPageFromRequest((HttpServletRequest) servletRequest);
        var homePage = (((HttpServletRequest) servletRequest).getContextPath()) + UrlPath.HOME;
        if (filterCondition(servletRequest, requestedWebPage, homePage)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            var prevPage = ((HttpServletRequest) servletRequest).getHeader(REFERER);
            ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : homePage);
        }
    }

    private String getWebPageFromRequest(HttpServletRequest servletRequest) {
        var uri = servletRequest.getRequestURI();
        var params = servletRequest.getQueryString();
        String requestedWebPage;
        if (params != null) {
            requestedWebPage = uri + "?" + params;
        } else {
            requestedWebPage = uri;
        }
        return requestedWebPage;
    }

    private boolean filterCondition(ServletRequest servletRequest, String requestedWebPage, String homePage) {
        return homePage.equals(requestedWebPage) ||
               isPublicCmd(requestedWebPage) ||
               (isUserLoggedIn(servletRequest) && hasRight(servletRequest));
    }

    private boolean isPublicCmd(String addressString) {
        return PUBLIC_CMD.stream().anyMatch(addressString::contains);
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        var user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute(AttributeName.USER);
        return user != null;
    }

    private boolean hasRight(ServletRequest servletRequest) {
        var user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute(AttributeName.USER);
        return isAdmin(user) || isLibrarian(user);
    }

    private boolean isAdmin(UserDto user) {
        return ADMIN.equals(user.getRole());
    }

    private boolean isLibrarian(UserDto user) {
        return LIBRARIAN.equals(user.getRole());
    }
}
