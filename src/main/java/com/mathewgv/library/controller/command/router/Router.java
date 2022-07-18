package com.mathewgv.library.controller.command.router;

public class Router {

    private final String webPage;
    private final RoutingType routingType;

    public Router(String webPage, RoutingType routingType) {
        this.webPage = webPage;
        this.routingType = routingType;
    }

    public String getWebPage() {
        return webPage;
    }

    public RoutingType getRoutingType() {
        return routingType;
    }
}
