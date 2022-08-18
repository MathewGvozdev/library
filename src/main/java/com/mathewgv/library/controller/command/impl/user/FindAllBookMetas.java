package com.mathewgv.library.controller.command.impl.user;

import com.mathewgv.library.controller.command.Command;
import com.mathewgv.library.controller.command.router.Router;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.service.BookService;
import com.mathewgv.library.service.dto.BookDto;
import com.mathewgv.library.service.exception.ServiceException;
import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.AttributeName;
import com.mathewgv.library.util.JspHelper;
import com.mathewgv.library.util.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class FindAllBookMetas implements Command {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final Integer SHOWED_BOOK_METAS_LIMIT = 10;
    private static final String PAGE = "page";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            var page = Integer.parseInt(req.getParameter(PAGE));
            var bookService = serviceFactory.getBookService();
            var showedBookMetas = bookService.findAllBookMetas(page, SHOWED_BOOK_METAS_LIMIT);
            var totalBookMetas = bookService.findAllBookMetas();

            req.setAttribute(AttributeName.PAGES, countPages(totalBookMetas));
            req.setAttribute(AttributeName.BOOK_METAS, showedBookMetas);
            return new Router(JspHelper.getPath(JspPath.FIND_ALL_BOOK_METAS), RoutingType.FORWARD);
        } catch (ServiceException | NumberFormatException e) {
            log.error("Failure to find all book-metas", e);
            req.setAttribute(AttributeName.ERROR, "Error in searching book metas");
            return new Router(JspHelper.getErrorPath(), RoutingType.ERROR);
        }
    }

    @SneakyThrows
    private void writeImage(InputStream image, HttpServletResponse resp) {
        try (image; var outputStream = resp.getOutputStream()) {
            int currentByte;
            while ((currentByte = image.read()) != -1) {
                outputStream.write(currentByte);
            }
        }
    }

    private Integer countPages(List<BookDto> totalBookMetas) {
        int pages;
        if (totalBookMetas.size() % SHOWED_BOOK_METAS_LIMIT == 0) {
            pages = totalBookMetas.size() / SHOWED_BOOK_METAS_LIMIT;
        } else {
            pages = totalBookMetas.size() / SHOWED_BOOK_METAS_LIMIT + 1;
        }
        return pages;
    }
}
