package com.mathewgv.library.controller;

import com.mathewgv.library.controller.command.CommandProvider;
import com.mathewgv.library.controller.command.router.RoutingType;
import com.mathewgv.library.dao.exception.ConnectionPoolException;
import com.mathewgv.library.dao.transaction.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/home")
@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@Slf4j
public class Controller extends HttpServlet {

    private final CommandProvider provider = CommandProvider.getInstance();

    private static final String COMMAND = "cmd";

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ConnectionPool.getInstance().loadDriver();
            ConnectionPool.getInstance().initConnectionPool();
            log.info("Connection pool is initialized");
        } catch (ConnectionPoolException e) {
            log.error("Error while initializing connection pool", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter(COMMAND);
        var router = provider.getCommand(command).execute(req, resp);
        var routingType = router.getRoutingType();

        if (routingType.equals(RoutingType.FORWARD)) {
            req.getRequestDispatcher(router.getWebPage()).forward(req, resp);
        } else if (routingType.equals(RoutingType.REDIRECT)) {
            resp.sendRedirect(router.getWebPage());
        } else if (routingType.equals(RoutingType.ERROR)) {
            log.error("Error while processing request: {}", router);
            req.getRequestDispatcher(router.getWebPage()).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().closePool();
            log.info("Connection pool is destroyed");
        } catch (ConnectionPoolException e) {
            log.error("Error while destroying connection pool", e);
            throw new RuntimeException(e);
        }
        super.destroy();
    }
}

