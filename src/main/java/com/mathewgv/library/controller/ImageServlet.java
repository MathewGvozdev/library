package com.mathewgv.library.controller;

import com.mathewgv.library.service.factory.ServiceFactory;
import com.mathewgv.library.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

@WebServlet(UrlPath.IMAGES + "/*")
@Slf4j
public class ImageServlet extends HttpServlet {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String RESP_CONTENT_TYPE = "application/octet-stream";

    @Override
    public void init() throws ServletException {
        super.init();
        log.info("ImageServlet is initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        var requestUri = req.getRequestURI();
        var imagePath = requestUri.replace(UrlPath.IMAGES, "");
        var imageService = serviceFactory.getImageService();
        imageService.get(imagePath)
                .ifPresentOrElse(image -> {
                    resp.setContentType(RESP_CONTENT_TYPE);
                    writeImage(image, resp);
                }, () -> resp.setStatus(404));
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

    @Override
    public void destroy() {
        super.destroy();
        log.info("ImageServlet is destroyed");
    }
}
