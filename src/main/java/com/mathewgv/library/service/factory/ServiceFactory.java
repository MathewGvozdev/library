package com.mathewgv.library.service.factory;

import com.mathewgv.library.service.BookService;
import com.mathewgv.library.service.ImageService;
import com.mathewgv.library.service.OrderService;
import com.mathewgv.library.service.UserService;
import com.mathewgv.library.service.impl.BookServiceImpl;
import com.mathewgv.library.service.impl.ImageServiceImpl;
import com.mathewgv.library.service.impl.OrderServiceImpl;
import com.mathewgv.library.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final BookService bookService = BookServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();
    private final OrderService orderService = OrderServiceImpl.getInstance();
    private final ImageService imageService = ImageServiceImpl.getInstance();

    private ServiceFactory() {
    }

    public BookService getBookService() {
        return bookService;
    }

    public UserService getUserService() {
        return userService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ImageService getImageService() {
        return imageService;
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
}
