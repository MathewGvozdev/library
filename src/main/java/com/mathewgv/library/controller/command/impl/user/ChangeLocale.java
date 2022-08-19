package com.mathewgv.library.controller.command.impl.user;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.UrlPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChangeLocale implements Command {

    private static final String LANGUAGE = "lang";
    private static final String REFERER = "referer";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            var language = req.getParameter(LANGUAGE);
            req.getSession().setAttribute(LANGUAGE, language);
            var prevPage = req.getHeader(REFERER);
            var page = prevPage != null ? prevPage : UrlPath.HOME;
            log.info("Locale is set to - {}", language);
            return new Router(page, RoutingType.REDIRECT);
        } catch (ServiceException e) {
            log.error("Failure to change locale", e);
            req.setAttribute(AttributeName.ERROR, "Error in login");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }
}
