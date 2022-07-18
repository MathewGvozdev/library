package com.mathewgv.library.controller;

import com.mathewgv.library.controller.command.CommandProvider;
import com.mathewgv.library.controller.command.router.RoutingType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home")
public class Controller extends HttpServlet {

    private final CommandProvider provider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("cmd");
        var router = provider.getCommand(command).execute(req, resp);
        var routingType = router.getRoutingType();

        if (routingType.equals(RoutingType.FORWARD)) {
            req.getRequestDispatcher(router.getWebPage()).forward(req, resp);
        } else if (routingType.equals(RoutingType.REDIRECT)) {
            resp.sendRedirect(router.getWebPage());
        } else if (routingType.equals(RoutingType.ERROR)) {
            req.getRequestDispatcher(router.getWebPage()).forward(req, resp);
        }
    }
}
